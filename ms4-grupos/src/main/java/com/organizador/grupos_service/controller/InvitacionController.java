package com.organizador.grupos_service.controller;

import com.organizador.grupos_service.dto.InvitacionDTO;
import com.organizador.grupos_service.service.InvitacionService;
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

@Tag(name = "Invitaciones", description = "Operaciones relacionadas con la gestión de invitaciones a grupos")
@RestController
@RequestMapping("api/v1/invitaciones")
public class InvitacionController {

    @Autowired
    private InvitacionService service;

    @Operation(summary = "Obtiene todas las invitaciones", description = "Retorna la lista completa de invitaciones registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<InvitacionDTO.Response> listar() {
        return service.getInvitaciones();
    }

    @Operation(summary = "Obtiene una invitación por su identificador", description = "Retorna la información de una invitación específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<InvitacionDTO.Response> buscarPorId(@PathVariable Integer id) {
        InvitacionDTO.Response invitacion = service.getInvitacion(id).orElseThrow();
        EntityModel<InvitacionDTO.Response> model = EntityModel.of(invitacion);
        model.add(
                linkTo(
                        methodOn(InvitacionController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8084/api/v1/invitaciones",
                        "todas-las-invitaciones"
                )
        );
        return model;
    }

    @Operation(summary = "Registra una nueva invitación", description = "Permite crear una nueva invitación en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public InvitacionDTO.Response agregar(@Valid @RequestBody InvitacionDTO.Request dto) {
        return service.saveInvitacion(dto);
    }

    @Operation(summary = "Actualiza una invitación existente", description = "Modifica la información de una invitación previamente registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public InvitacionDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody InvitacionDTO.Request dto) {
        return service.updateInvitacion(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina una invitación", description = "Elimina una invitación del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteInvitacion(id);
    }
}
