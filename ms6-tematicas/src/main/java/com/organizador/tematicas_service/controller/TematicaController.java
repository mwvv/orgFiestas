package com.organizador.tematicas_service.controller;

import com.organizador.tematicas_service.dto.TematicaDTO;
import com.organizador.tematicas_service.service.TematicaService;
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

@Tag(name = "Temáticas", description = "Operaciones relacionadas con la gestión de temáticas de fiestas")
@RestController
@RequestMapping("api/v1/tematicas")
public class TematicaController {

    @Autowired
    private TematicaService service;

    @Operation(summary = "Obtiene todas las temáticas", description = "Retorna la lista completa de temáticas registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<TematicaDTO.Response> listar() {
        return service.getTematicas();
    }

    @Operation(summary = "Obtiene una temática por su identificador", description = "Retorna la información de una temática específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<TematicaDTO.Response> buscarPorId(@PathVariable Integer id) {
        TematicaDTO.Response tematica = service.getTematica(id).orElseThrow();
        EntityModel<TematicaDTO.Response> model = EntityModel.of(tematica);
        model.add(
                linkTo(
                        methodOn(TematicaController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8086/api/v1/tematicas",
                        "todas-las-tematicas"
                )
        );
        return model;
    }

    @Operation(summary = "Registra una nueva temática", description = "Permite crear una nueva temática en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public TematicaDTO.Response agregar(@Valid @RequestBody TematicaDTO.Request dto) {
        return service.saveTematica(dto);
    }

    @Operation(summary = "Actualiza una temática existente", description = "Modifica la información de una temática previamente registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public TematicaDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody TematicaDTO.Request dto) {
        return service.updateTematica(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina una temática", description = "Elimina una temática del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteTematica(id);
    }
}
