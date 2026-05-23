package com.organizador.eventos_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class UsuarioClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String obtenerNombreUsuario(Integer usuarioId) {
        try {
            Map response = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/usuarios/" + usuarioId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return response != null ? (String) response.get("nombre") : "Desconocido";
        } catch (Exception e) {
            return "Desconocido";
        }
    }
}
