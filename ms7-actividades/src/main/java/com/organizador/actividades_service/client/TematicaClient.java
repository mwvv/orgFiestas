package com.organizador.actividades_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class TematicaClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreTematica(Integer tematicaId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8086/api/v1/tematicas/" + tematicaId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocida";
        } catch (Exception e) {
            return "Desconocida";
        }
    }
}
