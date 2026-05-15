package pe.product.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server()
                        .url("/api")
                        .description("API Gateway")))
                .info(new Info()
                        .title("Product Service API")
                        .version("AA3")
                        .description("Microservicio de productos tipo OLX: publicaciones, atributos, imágenes y ubicación."));
    }
}
