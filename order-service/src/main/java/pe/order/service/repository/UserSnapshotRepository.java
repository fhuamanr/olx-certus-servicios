package pe.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.order.service.entity.UserSnapshot;

public interface UserSnapshotRepository extends JpaRepository<UserSnapshot, Long> {
}