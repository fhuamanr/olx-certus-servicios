package pe.product.service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import pe.product.service.config.RabbitMQConfig;
import pe.product.service.event.ProductCreatedEvent;

@Component
public class ProductEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public ProductEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendProductCreatedEvent(ProductCreatedEvent event) {
        System.out.println("🔥 ENVIANDO EVENTO A RABBIT: " + event.getName());

        rabbitTemplate.convertAndSend(
        	    RabbitMQConfig.EXCHANGE,
        	    RabbitMQConfig.ROUTING_KEY,
        	    event,
        	    message -> {
        	        message.getMessageProperties().setHeader("__TypeId__", null); // 🔥 ESTE ES EL FIX REAL
        	        return message;
        	    }
        	);
    }
}