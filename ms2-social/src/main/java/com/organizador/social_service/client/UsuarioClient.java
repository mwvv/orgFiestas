package com.organizador.social_service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class UsuarioClient {

    private static final Logger log = LoggerFactory.getLogger(UsuarioClient.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreUsuario(Integer usuarioId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://usuarios-service/api/v1/usuarios/" + usuarioId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocido";
        } catch (Exception e) {
            log.error("Error al obtener usuario con id {}: {}", usuarioId, e.getMessage());
            return "Desconocido";
        }
    }
}