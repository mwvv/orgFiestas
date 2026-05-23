package com.organizador.actividades_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class ActividadDTO {

    @Data
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        private String nombre;

        @Size(max = 500)
        private String descripcion;

        @Positive
        private Integer duracionMinutos;

        @NotNull(message = "La tematica es obligatoria")
        private Integer tematicaId;

        @NotNull(message = "El tipo es obligatorio")
        private String tipo;
    }

    @Data
    public static class Response {
        private Integer id;
        private String nombre;
        private String descripcion;
        private Integer duracionMinutos;
        private Integer tematicaId;
        private String tipo;
        private LocalDate fechaRegistro;
    }
}
