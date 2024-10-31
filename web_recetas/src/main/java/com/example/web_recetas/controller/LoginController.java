package com.example.web_recetas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "auth/login"; // Asegúrate de que esta ruta sea correcta
    }
}
