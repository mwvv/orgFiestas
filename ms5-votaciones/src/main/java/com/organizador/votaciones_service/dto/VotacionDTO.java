package com.organizador.votaciones_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class VotacionDTO {

    @Data
    public static class Request {
        @NotNull(message = "El evento es obligatorio")
        private Integer eventoId;

        @NotBlank(message = "El tema es obligatorio")
        @Size(max = 100)
        private String tema;
    }

    @Data
    public static class Response {
        private Integer id;
        private Integer eventoId;
        private String tema;
        private String estado;
        private LocalDate fechaRegistro;
    }
}
