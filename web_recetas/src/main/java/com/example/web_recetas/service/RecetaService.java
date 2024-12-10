package com.example.web_recetas.service;

import com.example.web_recetas.model.Receta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RecetaService {

    @Value("${receta.api.url}")
    private String recetaApiUrl;

    public Page<Receta> getRecetasPaginadas(int page, int size, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Receta[]> response = restTemplate.exchange(
                recetaApiUrl,
                HttpMethod.GET,
                entity,
                Receta[].class
        );

        List<Receta> recetas = Arrays.asList(response.getBody());
        int start = Math.min(page * size, recetas.size());
        int end = Math.min((page + 1) * size, recetas.size());
        return new PageImpl<>(recetas.subList(start, end), PageRequest.of(page, size), recetas.size());
    }
}
