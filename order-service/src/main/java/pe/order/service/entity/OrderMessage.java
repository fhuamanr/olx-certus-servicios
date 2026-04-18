package pe.order.service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long authorId;
    private String content;
    private LocalDateTime timestamp;

    public OrderMessage(Long orderId, Long authorId, String content) {
        this.orderId = orderId;
        this.authorId = authorId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}