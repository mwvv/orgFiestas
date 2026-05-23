package com.organizador.eventos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "evento")
public class Evento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @NotNull(message = "La fecha del evento es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser pasada")
    private LocalDate fechaEvento;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotBlank(message = "El lugar es obligatorio")
    @Size(max = 200)
    private String lugar;

    @NotNull(message = "El anfitrion es obligatorio")
    private Integer anfitrionId;

    @Positive(message = "El maximo de invitados debe ser mayor a 0")
    private Integer maximoInvitados;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoEvento estado = EstadoEvento.ABIERTO;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoEvento { ABIERTO, CERRADO, CANCELADO, FINALIZADO }
}
