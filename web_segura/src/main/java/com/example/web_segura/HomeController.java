package com.example.web_segura;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@RequestParam(name="name", required=false, defaultValue="Seguridad y Calidad") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/")
    public String root(@RequestParam(name="name", required=false, defaultValue="Seguridad y Calidad") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Ruta a la página de login
    }
}
