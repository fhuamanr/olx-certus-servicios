package pe.product.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.product.service.entity.ProductImage;
import pe.product.service.repository.ProductImageRepository;

@Service
public class ProductImageService {

    private final ProductImageRepository repository;

    public ProductImageService(ProductImageRepository repository) {
        this.repository = repository;
    }

    public List<ProductImage> getAll() {
        return repository.findAll();
    }

    public ProductImage getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    
    public List<ProductImage> getByProductId(Long productId) {
        return repository.findByProduct_Id(productId);
    }

    public ProductImage save(ProductImage image) {

        // 🔥 VALIDACIÓN PRO
        if (image.getProduct() == null) {
            throw new RuntimeException("Product es requerido");
        }

        return repository.save(image);
    }

    public ProductImage update(Long id, ProductImage image) {
        return repository.findById(id).map(existing -> {

            existing.setUrl(image.getUrl());

            // 🔥 CAMBIO CLAVE
            existing.setIsPrimary(image.getIsPrimary());

            return repository.save(existing);

        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}