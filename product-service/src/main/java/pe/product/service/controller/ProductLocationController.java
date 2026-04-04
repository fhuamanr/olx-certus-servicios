package pe.product.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import pe.product.service.entity.ProductLocation;
import pe.product.service.services.ProductLocationService;

@RestController
@RequestMapping("/product-locations")
public class ProductLocationController {

    private final ProductLocationService service;

    public ProductLocationController(ProductLocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductLocation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductLocation getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/product/{productId}")
    public ProductLocation getByProduct(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @PostMapping
    public ProductLocation create(@RequestBody ProductLocation location) {
        return service.save(location);
    }

    @PutMapping("/{id}")
    public ProductLocation update(@PathVariable Long id, @RequestBody ProductLocation location) {
        return service.update(id, location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}