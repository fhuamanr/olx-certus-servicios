package pe.frontend.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pe.frontend.service.service.ProductClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pe.frontend.service.model.Product;

@Controller
public class ProductViewController {

    private final ProductClientService service;

    public ProductViewController(ProductClientService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("products", service.getAllProducts());

        return "index";
    }
    
    @PostMapping("/products/create")
    public String create(@ModelAttribute Product product) {

        service.createProduct(product);

        return "redirect:/";
    }
}