package pe.order.service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long sellerId;
    private Long buyerId;
    private String status;
    private LocalDateTime createdAt;

    public Order(Long productId, Long sellerId, Long buyerId) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }
}