package com.organizador.grupos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "grupo_evento")
public class GrupoEvento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventoId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El rol es obligatorio")
    private RolGrupo rol = RolGrupo.PARTICIPANTE;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum RolGrupo { ANFITRION, PARTICIPANTE }
}
