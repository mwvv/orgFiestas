package com.organizador.asignacion_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class ConsumibleClient {

    private static final Logger log = LoggerFactory.getLogger(ConsumibleClient.class);

    @Autowired
    @Qualifier("loadBalancedWebClientBuilder")
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreConsumible(Integer consumibleId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://consumibles-service/api/v1/consumibles/" + consumibleId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocido";
        } catch (Exception e) {
            log.error("Error al obtener consumible con id {}: {}", consumibleId, e.getMessage());
            return "Desconocido";
        }
    }
}