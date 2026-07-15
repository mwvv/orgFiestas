package com.organizador.social_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // <- anotacion necesaria
public class SocialServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialServiceApplication.class, args);
    }
}
