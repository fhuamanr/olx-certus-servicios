package pe.product.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.product.service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
