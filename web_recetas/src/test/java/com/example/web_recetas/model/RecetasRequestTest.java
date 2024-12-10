package com.example.web_recetas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecetasRequestTest {

    @Test
    public void testGettersAndSetters() {
        // Crea una instancia de RecetasRequest
        RecetasRequest receta = new RecetasRequest();

        // Define valores de prueba
        String nombre = "Empanadas de Pino";
        String ingredientes = "Carne molida, cebolla, huevo duro, pasas, aceitunas, especias, masa para empanadas";
        String instrucciones = "Preparar el pino, rellenar la masa, sellar y hornear hasta que estén doradas.";
        String tipoCocina = "Chilena";
        String paisOrigen = "Chile";
        String tiempoCoccion = "40 minutos";
        String dificultad = "Media";
        String usuario = "chef_pedro";

        // Establece los valores usando los setters
        receta.setNombre(nombre);
        receta.setIngredientes(ingredientes);
        receta.setInstrucciones(instrucciones);
        receta.setTipoCocina(tipoCocina);
        receta.setPaisOrigen(paisOrigen);
        receta.setTiempoCoccion(tiempoCoccion);
        receta.setDificultad(dificultad);
        receta.setUsuario(usuario);

        // Verifica los valores usando los getters
        assertEquals(nombre, receta.getNombre(), "El getter de nombre no devuelve el valor esperado.");
        assertEquals(ingredientes, receta.getIngredientes(), "El getter de ingredientes no devuelve el valor esperado.");
        assertEquals(instrucciones, receta.getInstrucciones(), "El getter de instrucciones no devuelve el valor esperado.");
        assertEquals(tipoCocina, receta.getTipoCocina(), "El getter de tipoCocina no devuelve el valor esperado.");
        assertEquals(paisOrigen, receta.getPaisOrigen(), "El getter de paisOrigen no devuelve el valor esperado.");
        assertEquals(tiempoCoccion, receta.getTiempoCoccion(), "El getter de tiempoCoccion no devuelve el valor esperado.");
        assertEquals(dificultad, receta.getDificultad(), "El getter de dificultad no devuelve el valor esperado.");
        assertEquals(usuario, receta.getUsuario(), "El getter de usuario no devuelve el valor esperado.");
    }

    @Test
    public void testConstructorCompleto() {
        // Define valores de prueba
        String nombre = "Empanadas de Pino";
        String ingredientes = "Carne molida, cebolla, huevo duro, pasas, aceitunas, especias, masa para empanadas";
        String instrucciones = "Preparar el pino, rellenar la masa, sellar y hornear hasta que estén doradas.";
        String tipoCocina = "Chilena";
        String paisOrigen = "Chile";
        String tiempoCoccion = "40 minutos";
        String dificultad = "Media";
        String usuario = "chef_pedro";

        // Crea una instancia usando el constructor completo
        RecetasRequest receta = new RecetasRequest(nombre, ingredientes, instrucciones, tipoCocina, paisOrigen, tiempoCoccion, dificultad, usuario);

        // Verifica los valores usando los getters
        assertEquals(nombre, receta.getNombre(), "El getter de nombre no devuelve el valor esperado.");
        assertEquals(ingredientes, receta.getIngredientes(), "El getter de ingredientes no devuelve el valor esperado.");
        assertEquals(instrucciones, receta.getInstrucciones(), "El getter de instrucciones no devuelve el valor esperado.");
        assertEquals(tipoCocina, receta.getTipoCocina(), "El getter de tipoCocina no devuelve el valor esperado.");
        assertEquals(paisOrigen, receta.getPaisOrigen(), "El getter de paisOrigen no devuelve el valor esperado.");
        assertEquals(tiempoCoccion, receta.getTiempoCoccion(), "El getter de tiempoCoccion no devuelve el valor esperado.");
        assertEquals(dificultad, receta.getDificultad(), "El getter de dificultad no devuelve el valor esperado.");
        assertEquals(usuario, receta.getUsuario(), "El getter de usuario no devuelve el valor esperado.");
    }
}
