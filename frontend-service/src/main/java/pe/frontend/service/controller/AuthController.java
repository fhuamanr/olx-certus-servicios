package pe.frontend.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import pe.frontend.service.model.LoginRequest;
import pe.frontend.service.model.LoginResponse;
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
    public String register(@ModelAttribute User user,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            service.register(user);
            redirectAttributes.addFlashAttribute("success", "Cuenta creada correctamente. Ya puedes iniciar sesión.");
            return "redirect:/login";
        } catch (RestClientResponseException e) {
            model.addAttribute("user", user);
            model.addAttribute("error", resolveRegisterError(e));
            return "register";
        }
    }
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        try {
            LoginResponse user = service.login(request);
            session.setAttribute("loggedUser", user);
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private String resolveRegisterError(RestClientResponseException e) {
        String response = e.getResponseBodyAsString();

        if (response != null && response.contains("Email already exists")) {
            return "El correo ya existe. Usa otro correo o inicia sesión.";
        }

        if (e.getStatusCode().is4xxClientError()) {
            return "No se pudo crear la cuenta. Revisa los datos ingresados.";
        }

        return "No se pudo crear la cuenta. Intenta nuevamente.";
    }
}
