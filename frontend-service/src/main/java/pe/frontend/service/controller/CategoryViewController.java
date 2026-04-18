package pe.frontend.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pe.frontend.service.model.Category;
import pe.frontend.service.service.CategoryClientService;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    private final CategoryClientService service;

    public CategoryViewController(CategoryClientService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", service.getAll());
        return "categories";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "create-category";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        service.save(category);
        return "redirect:/categories";
    }
}