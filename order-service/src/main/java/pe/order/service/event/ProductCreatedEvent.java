package pe.order.service.event;

public class ProductCreatedEvent {

    private Long productId;
    private Long sellerId;
    private String name;
    private Double price;

    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(Long productId, Long sellerId, String name, Double price) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.name = name;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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
}