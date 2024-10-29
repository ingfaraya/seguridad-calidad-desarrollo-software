package com.example.web_recetas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Arrays;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("recetasPopulares", getRecetasPopulares());
        model.addAttribute("banners", getBanners());
        return "home";
    }

    @GetMapping("/buscar")
    public String buscarRecetas() {
        return "buscar";
    }

    private List<String> getRecetasPopulares() {
        return Arrays.asList("Receta 1", "Receta 2", "Receta 3");
    }

    private List<String> getBanners() {
        return Arrays.asList("Banner 1", "Banner 2");
    }
}
