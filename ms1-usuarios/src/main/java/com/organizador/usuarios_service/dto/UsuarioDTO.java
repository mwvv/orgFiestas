package com.organizador.usuarios_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

public class UsuarioDTO {

    @Data
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        private String nombre;

        @Email(message = "El email debe tener un formato valido")
        @NotBlank(message = "El email es obligatorio")
        private String email;

        @NotBlank(message = "La contrasena es obligatoria")
        @Size(min = 6)
        private String contrasena;

        private String fotoPerfil;

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past
        private LocalDate fechaNacimiento;
    }

    @Data
    public static class Response {
        private Integer id;
        private String nombre;
        private String email;
        private String fotoPerfil;
        private LocalDate fechaNacimiento;
        private String estado;
        private LocalDate fechaRegistro;
    }
}
