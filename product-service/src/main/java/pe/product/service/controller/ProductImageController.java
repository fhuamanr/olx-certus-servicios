package pe.product.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import pe.product.service.entity.ProductImage;
import pe.product.service.services.ProductImageService;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {

    private final ProductImageService service;

    public ProductImageController(ProductImageService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductImage> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductImage getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/product/{productId}")
    public List<ProductImage> getByProduct(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @PostMapping
    public ProductImage create(@RequestBody ProductImage image) {
        return service.save(image);
    }

    @PutMapping("/{id}")
    public ProductImage update(@PathVariable Long id, @RequestBody ProductImage image) {
        return service.update(id, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}