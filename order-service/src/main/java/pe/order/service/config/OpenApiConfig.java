package pe.order.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .version("AA3")
                        .description("Microservicio de órdenes para flujo tipo OLX: solicitudes de compra, mensajes, estados y coordinación entre comprador y vendedor."));
    }
}