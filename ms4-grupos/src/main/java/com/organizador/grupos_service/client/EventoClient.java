package com.organizador.grupos_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class EventoClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreEvento(Integer eventoId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/api/v1/eventos/" + eventoId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocido";
        } catch (Exception e) {
            return "Desconocido";
        }
    }
}
