package pe.user.service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import pe.user.service.config.RabbitMQConfig;
import pe.user.service.event.UserCreatedEvent;

@Component
public class UserEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.USER_EXCHANGE,
                RabbitMQConfig.USER_CREATED_ROUTING_KEY,
                event,
                message -> {
                    message.getMessageProperties().setHeader("__TypeId__", null);
                    return message;
                }
        );
    }
}