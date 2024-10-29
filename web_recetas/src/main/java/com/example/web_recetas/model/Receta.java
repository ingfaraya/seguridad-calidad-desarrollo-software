package com.example.web_recetas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre de la receta no puede estar vacío")
    @Size(max = 100, message = "El nombre de la receta no debe exceder los 100 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La descripción de la receta no puede estar vacía")
    @Size(max = 500, message = "La descripción de la receta no debe exceder los 500 caracteres")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotBlank(message = "Los ingredientes no pueden estar vacíos")
    @Size(max = 1000, message = "Los ingredientes no deben exceder los 1000 caracteres")
    @Column(name = "ingredientes", nullable = false)
    private String ingredientes;

    // Constructor vacío requerido por JPA
    public Receta() {}

    // Constructor con todos los atributos
    public Receta(String nombre, String descripcion, String ingredientes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
}
