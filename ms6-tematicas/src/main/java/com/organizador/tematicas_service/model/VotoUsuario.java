package com.organizador.tematicas_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "voto_usuario")
public class VotoUsuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La votacion es obligatoria")
    private Integer votacionId;

    @NotNull(message = "La opcion es obligatoria")
    private Integer opcionId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();
}
