package com.organizador.eventos_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventoDTO {

    @Data
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        private String nombre;

        @Size(max = 500)
        private String descripcion;

        @NotNull @FutureOrPresent
        private LocalDate fechaEvento;

        @NotNull
        private LocalTime horaInicio;

        @NotBlank @Size(max = 200)
        private String lugar;

        @NotNull(message = "El anfitrion es obligatorio")
        private Integer anfitrionId;

        @Positive
        private Integer maximoInvitados;
    }

    @Data
    public static class Response {
        private Integer id;
        private String nombre;
        private String descripcion;
        private LocalDate fechaEvento;
        private LocalTime horaInicio;
        private String lugar;
        private Integer anfitrionId;
        private String nombreAnfitrion;
        private Integer maximoInvitados;
        private String estado;
        private LocalDate fechaRegistro;
    }
}
