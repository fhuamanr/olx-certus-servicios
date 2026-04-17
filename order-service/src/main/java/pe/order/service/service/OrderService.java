package pe.order.service.service;

import org.springframework.stereotype.Service;
import pe.order.service.entity.Order;
import pe.order.service.entity.OrderMessage;
import pe.order.service.event.OrderCreatedEvent;
import pe.order.service.producer.OrderEventProducer;
import pe.order.service.repository.OrderMessageRepository;
import pe.order.service.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMessageRepository messageRepository;
    private final OrderEventProducer producer;

    public OrderService(OrderRepository orderRepository, OrderMessageRepository messageRepository, OrderEventProducer producer) {
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
        this.producer = producer;
    }

    public Order createOrder(Order order) {
        Order saved = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                saved.getId(),
                saved.getProductId(),
                saved.getSellerId(),
                saved.getBuyerId()
        );

        producer.sendOrderCreatedEvent(event);
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

    public OrderMessage sendMessage(Long orderId, Long authorId, String content) {
        OrderMessage message = new OrderMessage(orderId, authorId, content);
        return messageRepository.save(message);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        return orderRepository.findById(orderId).map(order -> {
            order.setStatus(status);
            return orderRepository.save(order);
        }).orElse(null);
    }
}