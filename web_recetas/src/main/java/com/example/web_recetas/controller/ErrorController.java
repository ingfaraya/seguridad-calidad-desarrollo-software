package com.example.web_recetas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        return "error";  // Devuelve la plantilla error.html para manejar errores
    }
}
