package pe.order.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import pe.order.service.config.RabbitMQConfig;
import pe.order.service.entity.UserSnapshot;
import pe.order.service.event.UserCreatedEvent;
import pe.order.service.repository.UserSnapshotRepository;

@Component
public class UserEventConsumer {

    private final UserSnapshotRepository repository;

    public UserEventConsumer(UserSnapshotRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handleUserCreated(UserCreatedEvent event) {

        UserSnapshot snapshot = new UserSnapshot();
        snapshot.setUserId(event.getUserId());
        snapshot.setName(event.getName());
        snapshot.setEmail(event.getEmail());
        snapshot.setPhone(event.getPhone());
        snapshot.setEnabled(event.getEnabled());

        repository.save(snapshot);

        System.out.println("Usuario recibido en order-service: " + event.getEmail());
    }
}