package pe.product.service.event;

public class ProductCreatedEvent {

    private Long productId;
    private Long userId;
    private String name;
    private Double price;

    public ProductCreatedEvent(Long productId, Long userId, String name, Double price) {
        this.productId = productId;
        this.userId = userId;
        this.name = name;
        this.price = price;
    }

    public ProductCreatedEvent() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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