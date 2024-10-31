package com.example.web_recetas.controller;

import com.example.web_recetas.model.Receta;
import com.example.web_recetas.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private RecetaRepository recetaRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("message", "Bienvenido a Web Recetas");
        return "home"; // Carga la plantilla 'home.html' desde la carpeta 'auth'
    }

    @GetMapping("/recetas")
    public String buscarRecetas(Model model) {
        List<Receta> recetas = recetaRepository.findAll();
        model.addAttribute("recetas", recetas);
        return "receta/recetas"; // Carga la plantilla 'recetas.html' desde la carpeta 'receta'
    }
}
