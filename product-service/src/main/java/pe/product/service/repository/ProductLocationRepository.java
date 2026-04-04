package pe.product.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.product.service.entity.ProductLocation;

public interface ProductLocationRepository extends JpaRepository<ProductLocation, Long> {

    Optional<ProductLocation> findByProductId(Long productId);
}