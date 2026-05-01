package pe.order.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import pe.order.service.config.RabbitMQConfig;
import pe.order.service.entity.ProductSnapshot;
import pe.order.service.event.ProductCreatedEvent;
import pe.order.service.repository.ProductSnapshotRepository;

@Component
public class ProductEventConsumer {

    private final ProductSnapshotRepository repository;

    public ProductEventConsumer(ProductSnapshotRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_CREATED_QUEUE)
    public void handleProductCreated(ProductCreatedEvent event) {

        ProductSnapshot snapshot = new ProductSnapshot();
        snapshot.setProductId(event.getProductId());
        snapshot.setName(event.getName());
        snapshot.setPrice(event.getPrice());
        snapshot.setSellerId(event.getSellerId());
        snapshot.setAvailable(true);

        repository.save(snapshot);
    }
}