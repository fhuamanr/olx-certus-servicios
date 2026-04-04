package pe.product.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import pe.product.service.entity.ProductAttribute;
import pe.product.service.services.ProductAttributeService;

@RestController
@RequestMapping("/product-attributes")
public class ProductAttributeController {

    private final ProductAttributeService service;

    public ProductAttributeController(ProductAttributeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductAttribute> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductAttribute getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/product/{productId}")
    public List<ProductAttribute> getByProduct(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @PostMapping
    public ProductAttribute create(@RequestBody ProductAttribute attribute) {
        return service.save(attribute);
    }

    @PutMapping("/{id}")
    public ProductAttribute update(@PathVariable Long id, @RequestBody ProductAttribute attribute) {
        return service.update(id, attribute);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}