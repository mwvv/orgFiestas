package com.organizador.consumibles_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class ConsumibleDTO {

    @Data
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        private String nombre;

        @Size(max = 200)
        private String descripcion;

        @NotNull(message = "La categoria es obligatoria")
        private String categoria;

        @PositiveOrZero
        private Double precio;
    }

    @Data
    public static class Response {
        private Integer id;
        private String nombre;
        private String descripcion;
        private String categoria;
        private Double precio;
        private LocalDate fechaRegistro;
    }
}
