package pe.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.user.service.entity.ProductEventLog;

public interface ProductEventLogRepository extends JpaRepository<ProductEventLog, Long> {
}