package pe.order.service.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent {

    private Long productId;
    private String name;
    private Double price;
    private Long sellerId;
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
    
    
}