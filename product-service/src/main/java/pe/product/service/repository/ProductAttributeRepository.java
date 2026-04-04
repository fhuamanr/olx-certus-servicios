package pe.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.product.service.entity.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

    // IMPORTANTE (con relación JPA)
    List<ProductAttribute> findByProduct_Id(Long productId);
}