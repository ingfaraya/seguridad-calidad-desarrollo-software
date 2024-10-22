package com.example.web_recetas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";  // Devuelve la plantilla login.html
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";  // Redirige a la página de login después de cerrar sesión
    }
}
