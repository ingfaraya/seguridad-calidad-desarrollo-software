package com.example.web_recetas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
    private final Model model = mock(Model.class);

    @Test
    public void handleNotFound_shouldReturn404View() {
        // Simula un NoHandlerFoundException
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/nonexistent-url", null);

        String viewName = exceptionHandler.handleNotFound(exception, model);

        assertEquals("error/404", viewName); // Verifica que la vista sea "error/404"
        verify(model).addAttribute("error", "Página no encontrada");
        verify(model).addAttribute("message", "La URL solicitada no existe.");
    }

    @Test
    public void handleUnauthorized_shouldReturn401View() {
        // Simula un AccessDeniedException
        AccessDeniedException exception = new AccessDeniedException("Acceso denegado");

        String viewName = exceptionHandler.handleUnauthorized(exception, model);

        assertEquals("error/401", viewName); // Verifica que la vista sea "error/401"
        verify(model).addAttribute("error", "No autorizado");
        verify(model).addAttribute("message", "No tienes permiso para acceder a esta página.");
    }

    @Test
    public void handleServerError_shouldReturn500View() {
        // Simula una excepción genérica
        Exception exception = new Exception("Error interno");

        String viewName = exceptionHandler.handleServerError(exception, model);

        assertEquals("error/500", viewName); // Verifica que la vista sea "error/500"
        verify(model).addAttribute("error", "Error interno del servidor");
        verify(model).addAttribute("message", "Ha ocurrido un error inesperado.");
    }
}
