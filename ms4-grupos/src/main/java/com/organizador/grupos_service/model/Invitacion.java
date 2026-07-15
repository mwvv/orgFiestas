package com.organizador.grupos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "invitacion")
public class Invitacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventoId;

    @NotNull(message = "El usuario invitado es obligatorio")
    private Integer usuarioInvitadoId;

    @NotNull(message = "El anfitrion es obligatorio")
    private Integer usuarioAnfitrionId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoInvitacion estado = EstadoInvitacion.PENDIENTE;

    @Size(max = 200)
    private String mensaje;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoInvitacion { PENDIENTE, ACEPTADA, RECHAZADA }
}
