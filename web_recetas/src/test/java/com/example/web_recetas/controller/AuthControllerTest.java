package com.example.web_recetas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    private final Model model = mock(Model.class);
    private final AuthController authController = new AuthController();

    @Test
    public void home_shouldReturnHomeView() {
        String viewName = authController.home(model);

        assertEquals("home", viewName); // Verifica que la vista devuelta sea "home"
        verify(model).addAttribute(eq("loginRequest"), any());
    }
}
