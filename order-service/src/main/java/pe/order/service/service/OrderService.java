package pe.order.service.service;

import org.springframework.stereotype.Service;

import pe.order.service.dto.OrderDetailResponse;
import pe.order.service.entity.Order;
import pe.order.service.entity.OrderMessage;
import pe.order.service.entity.ProductSnapshot;
import pe.order.service.entity.UserSnapshot;
import pe.order.service.enums.OrderStatus;
import pe.order.service.event.OrderCreatedEvent;
import pe.order.service.producer.OrderEventProducer;
import pe.order.service.repository.OrderMessageRepository;
import pe.order.service.repository.OrderRepository;
import pe.order.service.repository.ProductSnapshotRepository;
import pe.order.service.repository.UserSnapshotRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMessageRepository messageRepository;
    private final ProductSnapshotRepository snapshotRepository;
    private final OrderEventProducer producer;
    private final UserSnapshotRepository userSnapshotRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderMessageRepository messageRepository,
                        ProductSnapshotRepository snapshotRepository,
                        OrderEventProducer producer,
                        UserSnapshotRepository userSnapshotRepository) {
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
        this.snapshotRepository = snapshotRepository;
        this.producer = producer;
        this.userSnapshotRepository = userSnapshotRepository;
    }

    public Order createOrder(Order order) {

        ProductSnapshot product = snapshotRepository
                .findById(order.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not available"));

        UserSnapshot buyer = userSnapshotRepository
                .findById(order.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        if (!Boolean.TRUE.equals(buyer.getEnabled())) {
            throw new RuntimeException("Buyer is disabled");
        }

        if (product.getSellerId().equals(order.getBuyerId())) {
            throw new RuntimeException("Seller cannot buy own product");
        }

        order.setSellerId(product.getSellerId());
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        order.setStatus(OrderStatus.PENDING);

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
        OrderStatus newStatus;

        try {
            newStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid order status");
        }

        return orderRepository.findById(orderId).map(order -> {
            order.setStatus(newStatus);
            order.setUpdatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    public OrderDetailResponse getOrderDetail(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        UserSnapshot buyer = userSnapshotRepository.findById(order.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        UserSnapshot seller = userSnapshotRepository.findById(order.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        OrderDetailResponse response = new OrderDetailResponse();

        response.setOrderId(order.getId());
        response.setProductId(order.getProductId());
        response.setProductName(order.getProductName());
        response.setProductPrice(order.getProductPrice());

        response.setBuyerId(buyer.getUserId());
        response.setBuyerName(buyer.getName());

        response.setSellerId(seller.getUserId());
        response.setSellerName(seller.getName());

        response.setStatus(order.getStatus());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setDeliveryMethod(order.getDeliveryMethod());
        response.setMeetingPoint(order.getMeetingPoint());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        boolean showContact = order.getStatus().name().equals("ACCEPTED")
                || order.getStatus().name().equals("IN_COORDINATION")
                || order.getStatus().name().equals("COMPLETED");

        if (showContact) {
            response.setBuyerEmail(buyer.getEmail());
            response.setBuyerPhone(buyer.getPhone());
            response.setSellerEmail(seller.getEmail());
            response.setSellerPhone(seller.getPhone());
        }

        return response;
    }
}