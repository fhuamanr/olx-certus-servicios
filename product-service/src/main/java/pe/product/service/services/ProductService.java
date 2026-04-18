package pe.product.service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.product.service.dto.CategoryDTO;
import pe.product.service.dto.ImageDTO;
import pe.product.service.dto.ProductResponse;
import pe.product.service.entity.Product;
import pe.product.service.entity.ProductAttribute;
import pe.product.service.entity.ProductImage;
import pe.product.service.event.ProductCreatedEvent;
import pe.product.service.producer.ProductEventProducer;
import pe.product.service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductEventProducer producer;
    
    public ProductService(ProductRepository repository, ProductEventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    //Obtener todos los productos (listings)
    public List<Product> getAll() {
        return repository.findAll();
    }
    
    public List<ProductResponse> getAllDTO() {
        return getAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Obtener por ID
    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Crear producto (listing)
    public Product save(Product product) {

    	  // 🔥 SET RELACIÓN IMÁGENES
        if (product.getImages() != null) {
            for (ProductImage img : product.getImages()) {
                img.setProduct(product);
            }
        }

        // 🔥 SET RELACIÓN ATRIBUTOS
        if (product.getAttributes() != null) {
            for (ProductAttribute attr : product.getAttributes()) {
                attr.setProduct(product);
            }
        }

        // 🔥 SET RELACIÓN LOCATION
        if (product.getLocation() != null) {
            product.getLocation().setProduct(product);
        }
        product.setStatus("ACTIVE");
        product.setCreatedAt(LocalDateTime.now());

        Product saved = repository.save(product);

        // 🔥 ENVIAR EVENTO
        ProductCreatedEvent event = new ProductCreatedEvent(
                saved.getId(),
                saved.getUserId(),
                saved.getName(),
                saved.getPrice()
        );

        producer.sendProductCreatedEvent(event);

        return saved;
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
    
    public ProductResponse mapToDTO(Product p) {

        ProductResponse dto = new ProductResponse();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setUserId(p.getUserId());
        dto.setStatus(p.getStatus());
        dto.setCreatedAt(p.getCreatedAt());

        // CATEGORY
        if (p.getCategory() != null) {
            CategoryDTO c = new CategoryDTO();
            c.setId(p.getCategory().getId());
            c.setName(p.getCategory().getName());
            dto.setCategory(c);
        }

        // IMAGES
        if (p.getImages() != null) {
            dto.setImages(
                p.getImages().stream().map(img -> {
                    ImageDTO i = new ImageDTO();
                    i.setUrl(img.getUrl());
                    return i;
                }).toList()
            );
        }

        return dto;
    }
}