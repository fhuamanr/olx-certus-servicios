package pe.order.service.controller;

import org.springframework.web.bind.annotation.*;
import pe.order.service.entity.Order;
import pe.order.service.entity.OrderMessage;
import pe.order.service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Order> getOrdersByBuyer(@PathVariable Long buyerId) {
        return orderService.getOrdersByBuyer(buyerId);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Order> getOrdersBySeller(@PathVariable Long sellerId) {
        return orderService.getOrdersBySeller(sellerId);
    }

    @GetMapping("/{id}/messages")
    public List<OrderMessage> getMessages(@PathVariable Long id) {
        return orderService.getMessagesByOrder(id);
    }

    @PostMapping("/{id}/messages")
    public OrderMessage sendMessage(@PathVariable Long id, @RequestBody MessageRequest request) {
        return orderService.sendMessage(id, request.getAuthorId(), request.getContent());
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }

    public static class MessageRequest {
        private Long authorId;
        private String content;

        public Long getAuthorId() { return authorId; }
        public void setAuthorId(Long authorId) { this.authorId = authorId; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}