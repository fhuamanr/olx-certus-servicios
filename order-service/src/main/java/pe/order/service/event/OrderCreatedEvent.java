package pe.order.service.event;

public class OrderCreatedEvent {

    private Long orderId;
    private Long productId;
    private Long sellerId;
    private Long buyerId;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(Long orderId, Long productId, Long sellerId, Long buyerId) {
        this.orderId = orderId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getSellerId() { return sellerId; }
    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public Long getBuyerId() { return buyerId; }
    public void setBuyerId(Long buyerId) { this.buyerId = buyerId; }
}