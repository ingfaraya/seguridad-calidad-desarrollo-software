package com.example.web_recetas.controller;

import com.example.web_recetas.model.RecetasRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("loginRequest", new RecetasRequest());
        return "home";
    }
}
