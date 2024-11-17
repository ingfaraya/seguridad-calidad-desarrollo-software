package com.example.libros.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenProvider {

    private SecretKey key;

    /**
     * Carga la clave secreta desde las propiedades y la convierte en una clave válida.
     */
    @Value("${jwt.secret}")
    public void setJwtSecret(String jwtSecret) {
        if (jwtSecret == null || jwtSecret.isBlank()) {
            throw new IllegalArgumentException("El secreto JWT no puede ser nulo o vacío");
        }
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Extrae el nombre de usuario (subject) del token JWT.
     *
     * @param token Token JWT.
     * @return El nombre de usuario contenido en el token.
     */
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo extraer el nombre de usuario del token: " + e.getMessage(), e);
        }
    }

    /**
     * Valida el token JWT verificando la firma y su estructura.
     *
     * @param token Token JWT.
     * @return `true` si el token es válido, de lo contrario `false`.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true; // Si no hay excepciones, el token es válido.
        } catch (Exception e) {
            // Aquí puedes manejar excepciones específicas como ExpiredJwtException o MalformedJwtException si es necesario.
            return false;
        }
    }
}
