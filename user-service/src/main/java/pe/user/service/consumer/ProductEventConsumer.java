package pe.user.service.consumer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import pe.user.service.config.RabbitMQConfig;
import pe.user.service.entity.ProductEventLog;
import pe.user.service.event.ProductCreatedEvent;
import pe.user.service.repository.ProductEventLogRepository;

import com.fasterxml.jackson.databind.ObjectMapper; // ✅ FIX

@Component
public class ProductEventConsumer {
	
	private final ProductEventLogRepository repository;

    public ProductEventConsumer(ProductEventLogRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleProductCreated(ProductCreatedEvent event) {

        ProductEventLog log = new ProductEventLog();
        log.setProductId(event.getProductId());
        log.setUserId(event.getUserId());
        log.setName(event.getName());
        log.setPrice(event.getPrice());
        log.setReceivedAt(LocalDateTime.now());

        repository.save(log);

        System.out.println("💾 EVENTO GUARDADO EN BD");
    }
}