package pe.frontend.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pe.frontend.service.model.User;
import pe.frontend.service.service.UserClientService;

@Controller
public class AuthController {

    private final UserClientService service;

    public AuthController(UserClientService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        service.register(user);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}