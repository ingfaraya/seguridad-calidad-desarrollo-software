package com.example.web_recetas.service;

import com.example.web_recetas.model.Receta;
import com.example.web_recetas.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    // Obtener todas las recetas
    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    // Obtener una receta por ID
    public Receta getRecetaById(Long id) {
        Optional<Receta> receta = recetaRepository.findById(id);
        return receta.orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }

    // Crear una nueva receta
    public Receta createReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    // Actualizar una receta existente
    public Receta updateReceta(Long id, Receta recetaDetails) {
        Receta receta = getRecetaById(id);
        receta.setNombre(recetaDetails.getNombre());
        receta.setDescripcion(recetaDetails.getDescripcion());
        receta.setIngredientes(recetaDetails.getIngredientes());
        // Actualizar otros campos según sea necesario
        return recetaRepository.save(receta);
    }

    // Eliminar una receta por ID
    public void deleteReceta(Long id) {
        Receta receta = getRecetaById(id);
        recetaRepository.delete(receta);
    }
}
