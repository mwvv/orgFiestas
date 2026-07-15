package com.organizador.social_service.controller;

import com.organizador.social_service.dto.SolicitudDTO;
import com.organizador.social_service.service.SolicitudAmistadService;
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

@Tag(name = "Solicitudes de Amistad", description = "Operaciones relacionadas con la gestión de solicitudes de amistad")
@RestController
@RequestMapping("api/v1/solicitudes")
public class SolicitudAmistadController {

    @Autowired
    private SolicitudAmistadService service;

    @Operation(summary = "Obtiene todas las solicitudes de amistad", description = "Retorna la lista completa de solicitudes de amistad registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public List<SolicitudDTO.Response> listar() {
        return service.getSolicitudes();
    }

    @Operation(summary = "Obtiene una solicitud de amistad por su identificador", description = "Retorna la información de una solicitud de amistad específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<SolicitudDTO.Response> buscarPorId(@PathVariable Integer id) {
        SolicitudDTO.Response solicitud = service.getSolicitud(id).orElseThrow();
        EntityModel<SolicitudDTO.Response> model = EntityModel.of(solicitud);
        model.add(
                linkTo(
                        methodOn(SolicitudAmistadController.class)
                                .buscarPorId(id)
                ).withSelfRel()
        );
        model.add(
                Link.of(
                        "http://localhost:8082/api/v1/solicitudes",
                        "todas-las-solicitudes"
                )
        );
        return model;
    }

    @Operation(summary = "Registra una nueva solicitud de amistad", description = "Permite crear una nueva solicitud de amistad en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PostMapping
    public SolicitudDTO.Response agregar(@Valid @RequestBody SolicitudDTO.Request dto) {
        return service.saveSolicitud(dto);
    }

    @Operation(summary = "Actualiza una solicitud de amistad existente", description = "Modifica la información de una solicitud de amistad previamente registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos en la peticion")
    })
    @PutMapping("/{id}")
    public SolicitudDTO.Response editar(@PathVariable Integer id, @Valid @RequestBody SolicitudDTO.Request dto) {
        return service.updateSolicitud(id, dto).orElseThrow();
    }

    @Operation(summary = "Elimina una solicitud de amistad", description = "Elimina una solicitud de amistad del sistema utilizando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.deleteSolicitud(id);
    }
}
