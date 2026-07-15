package com.organizador.ruleta_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "ruleta_consumible")
public class RuletaConsumible {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventoId;

    @NotNull(message = "El consumible sorteado es obligatorio")
    private Integer consumibleId;

    @NotNull(message = "El usuario asignado es obligatorio")
    private Integer usuarioAsignadoId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoRuleta estado = EstadoRuleta.SORTEADO;

    private LocalDate fechaSorteo = LocalDate.now();
    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoRuleta { SORTEADO, CONFIRMADO, CANCELADO }
}
