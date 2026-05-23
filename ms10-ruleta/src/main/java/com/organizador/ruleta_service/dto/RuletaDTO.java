package com.organizador.ruleta_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class RuletaDTO {

    @Data
    public static class Request {
        @NotNull(message = "El evento es obligatorio")
        private Integer eventoId;

        @NotNull(message = "El consumible es obligatorio")
        private Integer consumibleId;

        @NotNull(message = "El usuario asignado es obligatorio")
        private Integer usuarioAsignadoId;
    }

    @Data
    public static class Response {
        private Integer id;
        private Integer eventoId;
        private Integer consumibleId;
        private String nombreConsumible;
        private Integer usuarioAsignadoId;
        private String nombreUsuario;
        private String estado;
        private LocalDate fechaSorteo;
        private LocalDate fechaRegistro;
    }
}
