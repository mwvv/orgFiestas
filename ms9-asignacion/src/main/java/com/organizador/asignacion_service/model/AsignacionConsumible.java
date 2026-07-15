package com.organizador.asignacion_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "asignacion_consumible")
public class AsignacionConsumible {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventoId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "El consumible es obligatorio")
    private Integer consumibleId;

    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoAsignacion estado = EstadoAsignacion.PENDIENTE;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoAsignacion { PENDIENTE, CONFIRMADA, ENTREGADA }
}
