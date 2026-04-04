package pe.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.product.service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Productos por usuario (vendedor)
    List<Product> findByUserId(Long userId);

    // Productos por categoría
    List<Product> findByCategoryId(Long categoryId);

    // Productos por estado (ACTIVE, SOLD, INACTIVE)
    List<Product> findByStatus(String status);

}