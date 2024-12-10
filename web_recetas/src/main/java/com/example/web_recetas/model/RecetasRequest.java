package com.example.web_recetas.model;

public class RecetasRequest {
    private String nombre;
    private String ingredientes;
    private String instrucciones;
    private String tipoCocina;
    private String paisOrigen;
    private String tiempoCoccion;
    private String dificultad;
    private String usuario;

    // Constructor vacío
    public RecetasRequest() {}

    // Constructor completo
    public RecetasRequest(String nombre, String ingredientes, String instrucciones, String tipoCocina,
                          String paisOrigen, String tiempoCoccion, String dificultad, String usuario) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.instrucciones = instrucciones;
        this.tipoCocina = tipoCocina;
        this.paisOrigen = paisOrigen;
        this.tiempoCoccion = tiempoCoccion;
        this.dificultad = dificultad;
        this.usuario = usuario;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(String tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
