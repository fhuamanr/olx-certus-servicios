package pe.order.service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_messages")
@Data
@AllArgsConstructor
public class OrderMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long senderId;

    private String senderRole;

    private String message;

    private Boolean readMessage;

    private LocalDateTime createdAt;

    public OrderMessage() {
        this.readMessage = false;
        this.createdAt = LocalDateTime.now();
    }

    public OrderMessage(Long orderId, Long senderId, String message) {
        this.order = new Order();
        this.order.setId(orderId);
        this.senderId = senderId;
        this.senderRole = "BUYER";
        this.message = message;
        this.readMessage = false;
        this.createdAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getSenderRole() {
		return senderRole;
	}

	public void setSenderRole(String senderRole) {
		this.senderRole = senderRole;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getReadMessage() {
		return readMessage;
	}

	public void setReadMessage(Boolean readMessage) {
		this.readMessage = readMessage;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}