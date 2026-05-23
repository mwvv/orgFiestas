package com.organizador.social_service.controller;

import com.organizador.social_service.dto.SolicitudDTO;
import com.organizador.social_service.service.SolicitudAmistadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/solicitudes")
public class SolicitudAmistadController {

    @Autowired
    private SolicitudAmistadService service;

    @GetMapping
    public ResponseEntity<List<SolicitudDTO.Response>> listar() {
        List<SolicitudDTO.Response> lista = service.getSolicitudes();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getSolicitud(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO.Response> agregar(@Valid @RequestBody SolicitudDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveSolicitud(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteSolicitud(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitud no encontrada");
        }
    }
}
