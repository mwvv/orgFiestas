package com.organizador.tematicas_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "tematica")
public class Tematica {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @Size(max = 200)
    private String imagen;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();
}
