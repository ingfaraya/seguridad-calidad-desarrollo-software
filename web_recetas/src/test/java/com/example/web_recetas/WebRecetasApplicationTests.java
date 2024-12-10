package com.example.web_recetas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebRecetasApplicationTests {

    @Test
    void contextLoads() {
        // Si el contexto no carga, esta prueba fallará automáticamente
    }

    @Test
    void testMainMethod() {
        // Ejecutar el método main y verificar que no lanza excepciones
        String[] args = {};
        WebRecetasApplication.main(args);
    }
}