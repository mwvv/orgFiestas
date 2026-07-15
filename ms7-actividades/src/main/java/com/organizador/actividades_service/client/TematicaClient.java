package com.organizador.actividades_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class TematicaClient {

    private static final Logger log = LoggerFactory.getLogger(TematicaClient.class);

    @Autowired
    @Qualifier("loadBalancedWebClientBuilder")
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreTematica(Integer tematicaId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://tematicas-service/api/v1/tematicas/" + tematicaId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocida";
        } catch (Exception e) {
            log.error("Error al obtener tematica con id {}: {}", tematicaId, e.getMessage());
            return "Desconocida";
        }
    }
}