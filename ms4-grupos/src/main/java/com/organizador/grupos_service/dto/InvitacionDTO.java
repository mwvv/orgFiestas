package com.organizador.grupos_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class InvitacionDTO {

    @Data
    public static class Request {
        @NotNull(message = "El evento es obligatorio")
        private Integer eventoId;

        @NotNull(message = "El usuario invitado es obligatorio")
        private Integer usuarioInvitadoId;

        @NotNull(message = "El anfitrion es obligatorio")
        private Integer usuarioAnfitrionId;

        @Size(max = 200)
        private String mensaje;

        private String estado;
    }

    @Data
    public static class Response {
        private Integer id;
        private Integer eventoId;
        private String nombreEvento;
        private Integer usuarioInvitadoId;
        private Integer usuarioAnfitrionId;
        private String estado;
        private String mensaje;
        private LocalDate fechaRegistro;
    }
}
