package pe.product.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.product.service.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    
    List<ProductImage> findByProduct_Id(Long productId);
}