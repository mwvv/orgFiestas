package com.organizador.grupos_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // <- anotacion necesaria
public class GruposServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GruposServiceApplication.class, args);
    }
}
