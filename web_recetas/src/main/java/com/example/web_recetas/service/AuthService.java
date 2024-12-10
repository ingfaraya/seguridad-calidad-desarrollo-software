package com.example.web_recetas.service;

import com.example.web_recetas.model.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Value("${auth.api.url}")
    private String authApiUrl;

    public String login(LoginRequest loginRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(authApiUrl, loginRequest, String.class);
        return response.getBody();
    }
}
