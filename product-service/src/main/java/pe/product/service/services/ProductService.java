package pe.product.service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;


import pe.product.service.entity.Product;
import pe.product.service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;
    
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //Obtener todos los productos (listings)
    public List<Product> getAll() {
        return repository.findAll();
    }

    // Obtener por ID
    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Crear producto (listing)
    public Product save(Product product) {

       
        product.setStatus("ACTIVE");
        product.setCreatedAt(LocalDateTime.now());

        return repository.save(product);
    }

    // Actualizar producto
    public Product update(Long id, Product product) {

        return repository.findById(id).map(existing -> {

            existing.setName(product.getName()); 
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());

            return repository.save(existing);

        }).orElse(null);
    }

    // Eliminar producto
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // NUEVOS MÉTODOS (Marketplace)

    // Productos por usuario
    public List<Product> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    // Productos por categoría
    public List<Product> getByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    // Productos por estado
    public List<Product> getByStatus(String status) {
        return repository.findByStatus(status);
    }
}