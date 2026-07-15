package com.organizador.consumibles_service.controller;

import com.organizador.consumibles_service.dto.ConsumibleDTO;
import com.organizador.consumibles_service.service.ConsumibleService;
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

@Tag(name = "Consumibles", description = "Operaciones relacionadas con la gestión de consumibles")
@RestController
@RequestMapping("api/v1/consumibles")
public class ConsumibleController {

    @Autowired
    private ConsumibleService service;

    @Operation(summary = "Obtiene todos los consumibles", description = "Retorna la lista completa de consumibles registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<ConsumibleDTO.Response> listar() {
        return service.getConsumibles();
    }

    @Operation(summary = "Obtiene un consumible por su identificador", description = "Retorna la información de un consumible específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<ConsumibleDTO.Response> buscarPorId(@PathVariable Integer id) {
        ConsumibleDTO.Response consumible = service.getConsumible(id).orElseThrow();
        EntityModel<ConsumibleDTO.Response> model = EntityModel.of(consumible);
        model.add(
                linkTo(
                        methodOn(ConsumibleController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8088/api/v1/consumibles",
                        "todos-los-consumibles"
                )
        );
        return model;
    }

    @Operation(summary = "Registra un nuevo consumible", description = "Permite crear un nuevo consumible en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public ConsumibleDTO.Response agregar(@Valid @RequestBody ConsumibleDTO.Request dto) {
        return service.saveConsumible(dto);
    }

    @Operation(summary = "Actualiza un consumible existente", description = "Modifica la información de un consumible previamente registrado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public ConsumibleDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody ConsumibleDTO.Request dto) {
        return service.updateConsumible(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina un consumible", description = "Elimina un consumible del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteConsumible(id);
    }
}
