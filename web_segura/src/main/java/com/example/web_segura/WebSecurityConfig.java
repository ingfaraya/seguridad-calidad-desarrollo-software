package com.example.web_segura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactivamos temporalmente CSRF para descartar problemas relacionados
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/home", "/login", "/css/**").permitAll()  // Permitir acceso a CSS y rutas públicas
                                .anyRequest().authenticated()  // Todas las demás rutas requieren autenticación
                )
                .formLogin((form) -> form
                                .loginPage("/login")  // Ruta a la página de login
                                .defaultSuccessUrl("/home", true)  // Redirigir al home después de un login exitoso
                                .permitAll()  // Permitir acceso a la página de login sin autenticación
                )
                .logout((logout) -> logout
                                .logoutUrl("/logout")  // URL para cerrar sesión
                                .logoutSuccessUrl("/login?logout")  // Redirigir a login después de cerrar sesión
                                .permitAll()  // Permitir que cualquiera pueda cerrar sesión
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Configuramos usuarios en memoria para facilitar las pruebas
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("adminpassword"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
