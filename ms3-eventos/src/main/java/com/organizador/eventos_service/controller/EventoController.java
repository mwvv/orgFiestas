package com.organizador.eventos_service.controller;

import com.organizador.eventos_service.dto.EventoDTO;
import com.organizador.eventos_service.service.EventoService;
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

@Tag(name = "Eventos", description = "Operaciones relacionadas con la gestión de eventos")
@RestController
@RequestMapping("api/v1/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @Operation(summary = "Obtiene todos los eventos", description = "Retorna la lista completa de eventos registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<EventoDTO.Response> listar() {
        return service.getEventos();
    }

    @Operation(summary = "Obtiene un evento por su identificador", description = "Retorna la información de un evento específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<EventoDTO.Response> buscarPorId(@PathVariable Integer id) {
        EventoDTO.Response evento = service.getEvento(id).orElseThrow();
        EntityModel<EventoDTO.Response> model = EntityModel.of(evento);
        model.add(
                linkTo(
                        methodOn(EventoController.class).buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                "http://localhost:8083/api/v1/eventos",
                "todos-los-eventos"
                )
        );
        return model;
    }

    @Operation(summary = "Registra un nuevo evento", description = "Permite crear un nuevo evento en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public EventoDTO.Response agregar(@Valid @RequestBody EventoDTO.Request dto) {
        return service.saveEvento(dto);
    }

    @Operation(summary = "Actualiza un evento existente", description = "Modifica la información de un evento previamente registrado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public EventoDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody EventoDTO.Request dto) {
        return service.updateEvento(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina un evento", description = "Elimina un evento del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteEvento(id);
    }
}
