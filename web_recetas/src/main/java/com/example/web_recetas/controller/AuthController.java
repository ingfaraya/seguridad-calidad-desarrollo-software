package com.example.web_recetas.controller;

import com.example.web_recetas.model.LoginRequest;
import com.example.web_recetas.model.RecetasRequest;
import com.example.web_recetas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("loginRequest", new RecetasRequest());
        return "home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model) {
        try {
            String token = authService.login(loginRequest);
            model.addAttribute("token", token);
            return "redirect:/recetas";
        } catch (Exception e) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "home";
        }
    }
}
