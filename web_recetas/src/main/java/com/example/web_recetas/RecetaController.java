package com.example.web_recetas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecetaController {

    @GetMapping("/buscar")
    public String buscar() {
        return "buscar";  // Devuelve la página de búsqueda
    }

    @GetMapping("/recetas")
    public String verRecetas() {
        return "recetas";  // Devuelve la página de recetas
    }
}
