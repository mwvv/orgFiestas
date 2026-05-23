package com.organizador.votaciones_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "opcion_votacion")
public class OpcionVotacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La votacion es obligatoria")
    private Integer votacionId;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 100)
    private String descripcion;

    private Integer totalVotos = 0;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();
}
