package com.example.web_recetas.repository;

import com.example.web_recetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario.
}
