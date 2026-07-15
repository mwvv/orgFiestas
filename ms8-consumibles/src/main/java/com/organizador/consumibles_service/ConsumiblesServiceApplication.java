package com.organizador.consumibles_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // <- anotacion necesaria
public class ConsumiblesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumiblesServiceApplication.class, args);
    }
}
