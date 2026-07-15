package com.organizador.social_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class SolicitudDTO {

    @Data
    public static class Request {
        @NotNull(message = "El usuario emisor es obligatorio")
        private Integer usuarioEmisorId;

        @NotNull(message = "El usuario receptor es obligatorio")
        private Integer usuarioReceptorId;

        @Size(max = 200)
        private String mensaje;

        private String estado;
    }

    @Data
    public static class Response {
        private Integer id;
        private Integer usuarioEmisorId;
        private String nombreEmisor;
        private Integer usuarioReceptorId;
        private String estado;
        private String mensaje;
        private LocalDate fechaRegistro;
    }
}
