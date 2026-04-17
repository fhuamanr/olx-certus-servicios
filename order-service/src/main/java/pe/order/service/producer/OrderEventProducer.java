package pe.order.service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pe.order.service.config.RabbitMQConfig;
import pe.order.service.event.OrderCreatedEvent;

@Component
public class OrderEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("📦 ENVIANDO EVENTO A RABBIT: Order ID=" + event.getOrderId());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
    }
}