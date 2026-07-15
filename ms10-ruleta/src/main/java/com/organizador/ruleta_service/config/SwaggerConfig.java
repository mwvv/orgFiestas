package com.organizador.ruleta_service.config;

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
                        .title("Ruleta Service API")
                        .version("1.0.0")
                        .description("Microservicio para la gestión de sorteos de consumibles del sistema organizador de fiestas"));
    }
}
