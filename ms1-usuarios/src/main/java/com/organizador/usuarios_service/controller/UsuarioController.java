package com.organizador.usuarios_service.controller;

import com.organizador.usuarios_service.dto.UsuarioDTO;
import com.organizador.usuarios_service.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Obtiene todos los usuarios", description = "Retorna la lista completa de usuarios registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<UsuarioDTO.Response> listar() {
        return service.getUsuarios();
    }

    @Operation(summary = "Obtiene un usuario por su identificador", description = "Retorna la información de un usuario específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<UsuarioDTO.Response> buscarPorId(@PathVariable Integer id) {
        UsuarioDTO.Response usuario = service.getUsuario(id).orElseThrow();
        EntityModel<UsuarioDTO.Response> model = EntityModel.of(usuario);
        model.add(
                linkTo(
                        methodOn(UsuarioController.class)
                                .buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8081/api/v1/usuarios",
                        "todos-los-usuarios"
                )
        );
        return model;
    }

    @Operation(summary = "Registra un nuevo usuario", description = "Permite crear un nuevo usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public UsuarioDTO.Response agregar(@Valid @RequestBody UsuarioDTO.Request dto) {
        return service.saveUsuario(dto);
    }

    @Operation(summary = "Actualiza un usuario existente", description = "Modifica la información de un usuario previamente registrado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public UsuarioDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO.Request dto) {
        return service.updateUsuario(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteUsuario(id);
    }
}
