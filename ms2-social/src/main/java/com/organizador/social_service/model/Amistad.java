package com.organizador.social_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "amistad")
public class Amistad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El usuario 1 es obligatorio")
    private Integer usuarioId1;

    @NotNull(message = "El usuario 2 es obligatorio")
    private Integer usuarioId2;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoAmistad estado = EstadoAmistad.PENDIENTE;

    private LocalDate fechaAmistad;
    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoAmistad { PENDIENTE, ACEPTADA, BLOQUEADA }
}
