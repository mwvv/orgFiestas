package com.organizador.asignacion_service.controller;

import com.organizador.asignacion_service.dto.AsignacionDTO;
import com.organizador.asignacion_service.service.AsignacionConsumibleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/asignaciones")
public class AsignacionConsumibleController {

    @Autowired
    private AsignacionConsumibleService service;

    @GetMapping
    public ResponseEntity<List<AsignacionDTO.Response>> listar() {
        List<AsignacionDTO.Response> lista = service.getAsignaciones();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getAsignacion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AsignacionDTO.Response> agregar(@Valid @RequestBody AsignacionDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAsignacion(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteAsignacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignacion no encontrada");
        }
    }
}
