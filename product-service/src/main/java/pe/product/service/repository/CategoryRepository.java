package pe.product.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.product.service.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}