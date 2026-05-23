package com.organizador.ruleta_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class ConsumibleClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreConsumible(Integer consumibleId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8088/api/v1/consumibles/" + consumibleId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocido";
        } catch (Exception e) {
            return "Desconocido";
        }
    }
}
