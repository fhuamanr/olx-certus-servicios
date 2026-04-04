package pe.product.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import pe.product.service.entity.Product;
import pe.product.service.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Obtener todos los productos
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //Crear producto (listing)
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    //Obtener productos por usuario (vendedor)
    @GetMapping("/user/{userId}")
    public List<Product> getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    //Obtener productos por categoría
    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        return service.getByCategoryId(categoryId);
    }

    // Obtener productos por estado (ACTIVE, SOLD, etc.)
    @GetMapping("/status/{status}")
    public List<Product> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

}