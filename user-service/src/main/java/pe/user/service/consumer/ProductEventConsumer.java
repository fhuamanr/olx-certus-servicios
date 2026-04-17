package pe.user.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import pe.user.service.config.RabbitMQConfig;
import pe.user.service.event.ProductCreatedEvent;

@Component
public class ProductEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleProductCreated(ProductCreatedEvent event) {

        System.out.println("📥 EVENTO RECIBIDO:");
        System.out.println("Producto: " + event.getName());
        System.out.println("Usuario: " + event.getUserId());
    }
}