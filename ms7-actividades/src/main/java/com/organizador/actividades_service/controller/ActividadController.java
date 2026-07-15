package com.organizador.actividades_service.controller;

import com.organizador.actividades_service.dto.ActividadDTO;
import com.organizador.actividades_service.service.ActividadService;
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

@Tag(name = "Actividades", description = "Operaciones relacionadas con la gestión de actividades de eventos")
@RestController
@RequestMapping("api/v1/actividades")
public class ActividadController {

    @Autowired
    private ActividadService service;

    @Operation(summary = "Obtiene todas las actividades", description = "Retorna la lista completa de actividades registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<ActividadDTO.Response> listar() {
        return service.getActividades();
    }

    @Operation(summary = "Obtiene una actividad por su identificador", description = "Retorna la información de una actividad específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<ActividadDTO.Response> buscarPorId(@PathVariable Integer id) {
        ActividadDTO.Response actividad = service.getActividad(id).orElseThrow();
        EntityModel<ActividadDTO.Response> model = EntityModel.of(actividad);
        model.add(
                linkTo(
                        methodOn(ActividadController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8087/api/v1/actividades",
                        "todas-las-actividades"
                )
        );
        return model;
    }

    @Operation(summary = "Registra una nueva actividad", description = "Permite crear una nueva actividad en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public ActividadDTO.Response agregar(@Valid @RequestBody ActividadDTO.Request dto) {
        return service.saveActividad(dto);
    }

    @Operation(summary = "Actualiza una actividad existente", description = "Modifica la información de una actividad previamente registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public ActividadDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody ActividadDTO.Request dto) {
        return service.updateActividad(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina una actividad", description = "Elimina una actividad del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteActividad(id);
    }
}
