package pe.order.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_snapshot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSnapshot {

    @Id
    private Long productId;

    private String name;
    private Double price;
    private Long sellerId;

    private Boolean available;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
    
    
}