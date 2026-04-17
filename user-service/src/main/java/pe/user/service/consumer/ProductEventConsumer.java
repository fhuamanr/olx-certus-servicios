package pe.user.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import pe.user.service.event.ProductCreatedEvent;

@Component
public class ProductEventConsumer {

    @RabbitListener(queues = "product.created.queue")
    public void consume(ProductCreatedEvent event) {

        System.out.println("📥 USER-SERVICE RECIBIÓ EVENTO:");
        System.out.println("Producto: " + event.getName());
        System.out.println("UserId: " + event.getUserId());

        // Aquí luego puedes hacer lógica real:
        // - validar usuario
        // - guardar historial
        // - notificaciones
    }
}