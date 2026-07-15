package com.organizador.asignacion_service.controller;

import com.organizador.asignacion_service.dto.AsignacionDTO;
import com.organizador.asignacion_service.service.AsignacionConsumibleService;
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

@Tag(name = "Asignaciones", description = "Operaciones relacionadas con la gestión de asignaciones de consumibles")
@RestController
@RequestMapping("api/v1/asignaciones")
public class AsignacionConsumibleController {

    @Autowired
    private AsignacionConsumibleService service;

    @Operation(summary = "Obtiene todas las asignaciones", description = "Retorna la lista completa de asignaciones de consumibles registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<AsignacionDTO.Response> listar() {
        return service.getAsignaciones();
    }

    @Operation(summary = "Obtiene una asignación por su identificador", description = "Retorna la información de una asignación específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<AsignacionDTO.Response> buscarPorId(@PathVariable Integer id) {
        AsignacionDTO.Response asignacion = service.getAsignacion(id).orElseThrow();
        EntityModel<AsignacionDTO.Response> model = EntityModel.of(asignacion);
        model.add(
                linkTo(
                        methodOn(AsignacionConsumibleController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8089/api/v1/asignaciones",
                        "todas-las-asignaciones"
                )
        );
        return model;
    }

    @Operation(summary = "Registra una nueva asignación", description = "Permite crear una nueva asignación de consumible en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public AsignacionDTO.Response agregar(@Valid @RequestBody AsignacionDTO.Request dto) {
        return service.saveAsignacion(dto);
    }

    @Operation(summary = "Actualiza una asignación existente", description = "Modifica la información de una asignación previamente registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public AsignacionDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody AsignacionDTO.Request dto) {
        return service.updateAsignacion(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina una asignación", description = "Elimina una asignación del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteAsignacion(id);
    }
}
