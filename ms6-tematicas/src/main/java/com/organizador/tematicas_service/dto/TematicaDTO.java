package com.organizador.tematicas_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class TematicaDTO {

    @Data
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        private String nombre;

        @Size(max = 500)
        private String descripcion;

        @Size(max = 200)
        private String imagen;
    }

    @Data
    public static class Response {
        private Integer id;
        private String nombre;
        private String descripcion;
        private String imagen;
        private LocalDate fechaRegistro;
    }
}
