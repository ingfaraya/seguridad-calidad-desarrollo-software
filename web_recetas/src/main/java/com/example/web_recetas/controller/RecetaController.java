package com.example.web_recetas.controller;

import com.example.web_recetas.model.Receta;
import com.example.web_recetas.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public String listarRecetas(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "6") int size,
                                 @RequestParam(required = false) String token,
                                 Model model) {
        Page<Receta> recetas = recetaService.getRecetasPaginadas(page, size, token);
        model.addAttribute("recetas", recetas.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", recetas.getTotalPages());
        return "home";
    }
}
