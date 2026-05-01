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

        System.out.println("PRODUCT EVENT RECIBIDO EN ORDER:");
        System.out.println("productId: " + event.getProductId());
        System.out.println("sellerId: " + event.getSellerId());
        System.out.println("name: " + event.getName());
        System.out.println("price: " + event.getPrice());

        ProductSnapshot snapshot = new ProductSnapshot();
        snapshot.setProductId(event.getProductId());
        snapshot.setSellerId(event.getSellerId());
        snapshot.setName(event.getName());
        snapshot.setPrice(event.getPrice());
        snapshot.setAvailable(true);

        repository.save(snapshot);

        System.out.println("PRODUCT SNAPSHOT GUARDADO: " + event.getName());
    }
}