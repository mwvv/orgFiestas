package com.organizador.social_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Service API")
                        .version("1.0.0")
                        .description("Microservicio para la gestión de solicitudes de amistad del sistema organizador de fiestas"));
    }
}
