package pe.product.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.product.service.entity.ProductLocation;
import pe.product.service.repository.ProductLocationRepository;

@Service
public class ProductLocationService {

    private final ProductLocationRepository repository;

    public ProductLocationService(ProductLocationRepository repository) {
        this.repository = repository;
    }

    public List<ProductLocation> getAll() {
        return repository.findAll();
    }

    public ProductLocation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ProductLocation getByProductId(Long productId) {
        return repository.findByProductId(productId).orElse(null);
    }

    public ProductLocation save(ProductLocation location) {
        return repository.save(location);
    }

    public ProductLocation update(Long id, ProductLocation location) {
        return repository.findById(id).map(existing -> {
            existing.setCity(location.getCity());
            existing.setAddress(location.getAddress());
            existing.setLatitude(location.getLatitude());
            existing.setLongitude(location.getLongitude());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}