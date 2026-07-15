package com.organizador.actividades_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "actividad_evento")
public class ActividadEvento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La actividad es obligatoria")
    private Integer actividadId;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventoId;

    private LocalTime horaInicio;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoActividad estado = EstadoActividad.PENDIENTE;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoActividad { PENDIENTE, EN_CURSO, FINALIZADA, CANCELADA }
}
