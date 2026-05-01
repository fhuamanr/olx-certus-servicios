package pe.order.service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Eventos que order-service PUBLICA
    public static final String ORDER_CREATED_QUEUE = "order.created.queue";
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    // Eventos que order-service CONSUME desde product-service
    public static final String PRODUCT_CREATED_QUEUE = "product.created.queue";
    public static final String PRODUCT_EXCHANGE = "product.exchange";
    public static final String PRODUCT_CREATED_ROUTING_KEY = "product.created";

    // Cola propia de order-service
    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_CREATED_QUEUE).build();
    }

    @Bean
    public TopicExchange orderExchange() {
        return ExchangeBuilder.topicExchange(ORDER_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding orderCreatedBinding() {
        return BindingBuilder
                .bind(orderCreatedQueue())
                .to(orderExchange())
                .with(ORDER_CREATED_ROUTING_KEY);
    }

    // Cola para consumir eventos de product-service
    @Bean
    public Queue productCreatedQueue() {
        return QueueBuilder.durable(PRODUCT_CREATED_QUEUE).build();
    }

    @Bean
    public TopicExchange productExchange() {
        return ExchangeBuilder.topicExchange(PRODUCT_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding productCreatedBinding() {
        return BindingBuilder
                .bind(productCreatedQueue())
                .to(productExchange())
                .with(PRODUCT_CREATED_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}