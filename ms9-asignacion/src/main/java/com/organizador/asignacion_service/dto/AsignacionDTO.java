package com.organizador.asignacion_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class AsignacionDTO {

    @Data
    public static class Request {
        @NotNull(message = "El evento es obligatorio")
        private Integer eventoId;

        @NotNull(message = "El usuario es obligatorio")
        private Integer usuarioId;

        @NotNull(message = "El consumible es obligatorio")
        private Integer consumibleId;

        @Positive(message = "La cantidad debe ser mayor a 0")
        private Integer cantidad;

        private String estado;
    }

    @Data
    public static class Response {
        private Integer id;
        private Integer eventoId;
        private Integer usuarioId;
        private Integer consumibleId;
        private String nombreConsumible;
        private Integer cantidad;
        private String estado;
        private LocalDate fechaRegistro;
    }
}
