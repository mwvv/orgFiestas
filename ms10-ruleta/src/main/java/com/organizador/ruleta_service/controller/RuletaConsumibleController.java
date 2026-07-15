package com.organizador.ruleta_service.controller;

import com.organizador.ruleta_service.dto.RuletaDTO;
import com.organizador.ruleta_service.service.RuletaConsumibleService;
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

@Tag(name = "Ruleta de Consumibles", description = "Operaciones relacionadas con la gestión de sorteos de consumibles")
@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaConsumibleController {

    @Autowired
    private RuletaConsumibleService service;

    @Operation(summary = "Obtiene todos los sorteos", description = "Retorna la lista completa de sorteos de consumibles registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<RuletaDTO.Response> listar() {
        return service.getRuletas();
    }

    @Operation(summary = "Obtiene un sorteo por su identificador", description = "Retorna la información de un sorteo específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<RuletaDTO.Response> buscarPorId(@PathVariable Integer id) {
        RuletaDTO.Response ruleta = service.getRuleta(id).orElseThrow();
        EntityModel<RuletaDTO.Response> model = EntityModel.of(ruleta);
        model.add(
                linkTo(
                        methodOn(RuletaConsumibleController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8090/api/v1/ruleta",
                        "todos-los-sorteos"
                )
        );
        return model;
    }

    @Operation(summary = "Registra un nuevo sorteo", description = "Permite crear un nuevo sorteo de consumible en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public RuletaDTO.Response agregar(@Valid @RequestBody RuletaDTO.Request dto) {
        return service.saveRuleta(dto);
    }

    @Operation(summary = "Actualiza un sorteo existente", description = "Modifica la información de un sorteo previamente registrado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public RuletaDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody RuletaDTO.Request dto) {
        return service.updateRuleta(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina un sorteo", description = "Elimina un sorteo del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteRuleta(id);
    }
}
