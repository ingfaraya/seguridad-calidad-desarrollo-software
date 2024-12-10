package com.example.web_recetas.service;

import com.example.web_recetas.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioService {

    private final String USUARIO_API_URL = "http://localhost:8080/api/usuarios";

    public List<Usuario> listarUsuarios() {
        RestTemplate restTemplate = new RestTemplate();
        Usuario[] usuarios = restTemplate.getForObject(USUARIO_API_URL, Usuario[].class);
        return usuarios != null ? Arrays.asList(usuarios) : List.of();
    }
}
