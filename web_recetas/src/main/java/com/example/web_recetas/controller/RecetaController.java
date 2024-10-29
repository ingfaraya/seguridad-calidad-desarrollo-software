package com.example.web_recetas.controller;

import com.example.web_recetas.model.Receta;
import com.example.web_recetas.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
    @Autowired
    private RecetaRepository recetaRepository;

    @GetMapping
    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    @PostMapping
    public Receta createReceta(@RequestBody Receta receta) {
        return recetaRepository.save(receta);
    }

    @PutMapping("/{id}")
    public Receta updateReceta(@PathVariable Long id, @RequestBody Receta receta) {
        receta.setId(id);
        return recetaRepository.save(receta);
    }

    @DeleteMapping("/{id}")
    public void deleteReceta(@PathVariable Long id) {
        recetaRepository.deleteById(id);
    }
}
