package pe.order.service.service;

import org.springframework.stereotype.Service;
import pe.order.service.entity.Order;
import pe.order.service.entity.OrderMessage;
import pe.order.service.entity.ProductSnapshot;
import pe.order.service.event.OrderCreatedEvent;
import pe.order.service.producer.OrderEventProducer;
import pe.order.service.repository.OrderMessageRepository;
import pe.order.service.repository.OrderRepository;
import pe.order.service.repository.ProductSnapshotRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMessageRepository messageRepository;
    private final ProductSnapshotRepository snapshotRepository;
    private final OrderEventProducer producer;

    public OrderService(OrderRepository orderRepository,
                        OrderMessageRepository messageRepository,
                        ProductSnapshotRepository snapshotRepository,
                        OrderEventProducer producer) {
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
        this.snapshotRepository = snapshotRepository;
        this.producer = producer;
    }

    public Order createOrder(Order order) {

        ProductSnapshot product = snapshotRepository
                .findById(order.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not available"));

        order.setSellerId(product.getSellerId());
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());

        order.setStatus("PENDING");

        Order saved = orderRepository.save(order);

        producer.sendOrderCreatedEvent(
                new OrderCreatedEvent(
                        saved.getId(),
                        saved.getProductId(),
                        saved.getSellerId(),
                        saved.getBuyerId()
                )
        );

        return saved;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    public List<Order> getOrdersBySeller(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    public List<OrderMessage> getMessagesByOrder(Long orderId) {
        return messageRepository.findByOrderId(orderId);
    }

    public OrderMessage sendMessage(Long orderId, Long senderId, String senderRole, String content) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderMessage message = new OrderMessage();
        message.setOrder(order);
        message.setSenderId(senderId);
        message.setSenderRole(senderRole);
        message.setMessage(content);
        message.setReadMessage(false);
        message.setCreatedAt(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        return orderRepository.findById(orderId).map(order -> {
            order.setStatus(status);
            order.setUpdatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        }).orElse(null);
    }
}