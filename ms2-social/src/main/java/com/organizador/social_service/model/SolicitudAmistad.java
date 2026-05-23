package com.organizador.social_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "solicitud_amistad")
public class SolicitudAmistad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El usuario emisor es obligatorio")
    private Integer usuarioEmisorId;

    @NotNull(message = "El usuario receptor es obligatorio")
    private Integer usuarioReceptorId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    @Size(max = 200)
    private String mensaje;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoSolicitud { PENDIENTE, ACEPTADA, RECHAZADA }
}
