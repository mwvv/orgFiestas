package com.organizador.grupos_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class EventoClient {

    private static final Logger log = LoggerFactory.getLogger(EventoClient.class);

    @Autowired
    @Qualifier("loadBalancedWebClientBuilder")
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreEvento(Integer eventoId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://eventos-service/api/v1/eventos/" + eventoId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocido";
        } catch (Exception e) {
            log.error("Error al obtener evento con id {}: {}", eventoId, e.getMessage());
            return "Desconocido";
        }
    }
}