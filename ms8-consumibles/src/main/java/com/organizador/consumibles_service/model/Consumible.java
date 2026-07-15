package com.organizador.consumibles_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "consumible")
public class Consumible {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @Size(max = 200)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La categoria es obligatoria")
    private CategoriaConsumible categoria;

    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double precio;

    private boolean activo = true;
    private LocalDate fechaRegistro = LocalDate.now();

    public enum CategoriaConsumible { BEBIDA, COMIDA, SNACK, OTRO }
}
