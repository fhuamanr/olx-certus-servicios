package pe.product.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.product.service.entity.ProductAttribute;
import pe.product.service.repository.ProductAttributeRepository;

@Service
public class ProductAttributeService {

    private final ProductAttributeRepository repository;

    public ProductAttributeService(ProductAttributeRepository repository) {
        this.repository = repository;
    }

    public List<ProductAttribute> getAll() {
        return repository.findAll();
    }

    public ProductAttribute getById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public List<ProductAttribute> getByProductId(Long productId) {
        return repository.findByProduct_Id(productId);
    }

    public ProductAttribute save(ProductAttribute attribute) {
        return repository.save(attribute);
    }

    public ProductAttribute update(Long id, ProductAttribute attribute) {
        return repository.findById(id).map(existing -> {

            // CAMBIO CLAVE
            existing.setAttributeName(attribute.getAttributeName());
            existing.setAttributeValue(attribute.getAttributeValue());

            return repository.save(existing);

        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}