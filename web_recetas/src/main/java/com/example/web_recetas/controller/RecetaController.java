package com.example.web_recetas.controller;

import com.example.web_recetas.model.Receta;
import com.example.web_recetas.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    // Obtener todas las recetas, requiere autenticación
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        return ResponseEntity.ok(recetaService.getAllRecetas());
    }

    // Obtener una receta por ID, requiere autenticación
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.getRecetaById(id));
    }

    // Crear una nueva receta, requiere autenticación
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.createReceta(receta));
    }

    // Actualizar una receta existente, requiere autenticación
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta(@PathVariable Long id, @RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.updateReceta(id, receta));
    }

    // Eliminar una receta, requiere autenticación
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }
}
