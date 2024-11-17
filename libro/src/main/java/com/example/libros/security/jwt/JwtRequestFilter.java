package com.example.libros.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private SecretKey secretKey;

    /**
     * Configura la clave secreta desde las propiedades de la aplicación.
     */
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("La clave secreta JWT no puede ser nula o vacía.");
        }
        if (secret.length() < 32) {
            throw new IllegalArgumentException("La clave secreta JWT debe tener al menos 32 caracteres.");
        }
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Obtén el encabezado de autorización
        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        // Si se encuentra un token y no hay una autenticación en el contexto
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // Valida el token y extrae las reclamaciones
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                if (username != null) {
                    // Puedes agregar roles extraídos del token si es necesario
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            username, null, null);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Establece la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Maneja el token inválido
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado: " + e.getMessage());
                return;
            }
        }

        // Continúa con la cadena de filtros
        chain.doFilter(request, response);
    }
}
