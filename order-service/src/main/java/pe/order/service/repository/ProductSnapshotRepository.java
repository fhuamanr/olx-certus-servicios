package pe.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.order.service.entity.ProductSnapshot;

public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshot, Long> {
}