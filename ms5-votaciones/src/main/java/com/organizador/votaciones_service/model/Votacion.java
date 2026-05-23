package com.organizador.votaciones_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "votacion")
public class Votacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventoId;

    @NotBlank(message = "El tema es obligatorio")
    @Size(max = 100)
    private String tema;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoVotacion estado = EstadoVotacion.ABIERTA;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoVotacion { ABIERTA, CERRADA }
}
