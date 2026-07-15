package com.organizador.votaciones_service.controller;

import com.organizador.votaciones_service.dto.VotacionDTO;
import com.organizador.votaciones_service.service.VotacionService;
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

@Tag(name = "Votaciones", description = "Operaciones relacionadas con la gestión de votaciones")
@RestController
@RequestMapping("api/v1/votaciones")
public class VotacionController {

    @Autowired
    private VotacionService service;

    @Operation(summary = "Obtiene todas las votaciones", description = "Retorna la lista completa de votaciones registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<VotacionDTO.Response> listar() {
        return service.getVotaciones();
    }

    @Operation(summary = "Obtiene una votación por su identificador", description = "Retorna la información de una votación específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<VotacionDTO.Response> buscarPorId(@PathVariable Integer id) {
        VotacionDTO.Response votacion = service.getVotacion(id).orElseThrow();
        EntityModel<VotacionDTO.Response> model = EntityModel.of(votacion);
        model.add(
                linkTo(
                        methodOn(VotacionController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8085/api/v1/votaciones",
                        "todas-las-votaciones"
                )
        );
        return model;
    }

    @Operation(summary = "Registra una nueva votación", description = "Permite crear una nueva votación en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public VotacionDTO.Response agregar(@Valid @RequestBody VotacionDTO.Request dto) {
        return service.saveVotacion(dto);
    }

    @Operation(summary = "Actualiza una votación existente", description = "Modifica la información de una votación previamente registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public VotacionDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody VotacionDTO.Request dto) {
        return service.updateVotacion(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina una votación", description = "Elimina una votación del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteVotacion(id);
    }
}
