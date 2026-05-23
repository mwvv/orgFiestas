package com.organizador.usuarios_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;

    @Email(message = "El email debe tener un formato valido")
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contrasena es obligatoria")
    @Size(min = 6, message = "La contrasena debe tener al menos 6 caracteres")
    private String contrasena;

    @Size(max = 200, message = "La foto no puede superar 200 caracteres")
    private String fotoPerfil;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser pasada")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    private boolean activo = true;

    private LocalDate fechaRegistro = LocalDate.now();

    public enum EstadoUsuario {
        ACTIVO, INACTIVO, BLOQUEADO
    }
}
