package com.organizador.actividades_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "actividad")
public class Actividad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @Positive(message = "La duracion debe ser mayor a 0")
    private Integer duracionMinutos;

    @NotNull(message = "La tematica es obligatoria")
    private Integer tematicaId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo es obligatorio")
    private TipoActividad tipo;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum TipoActividad { JUEGO, BAILE, KARAOKE, CONCURSO, OTRO }
}
