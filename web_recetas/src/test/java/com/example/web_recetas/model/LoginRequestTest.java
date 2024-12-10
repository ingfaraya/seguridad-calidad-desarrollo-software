package com.example.web_recetas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {

    @Test
    public void testGettersAndSetters() {
        // Crea una instancia de LoginRequest
        LoginRequest loginRequest = new LoginRequest();

        // Configura los valores
        String username = "testUser";
        String password = "testPass";

        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        // Verifica los getters
        assertEquals(username, loginRequest.getUsername(), "El getter de username no devuelve el valor esperado.");
        assertEquals(password, loginRequest.getPassword(), "El getter de password no devuelve el valor esperado.");
    }
}
