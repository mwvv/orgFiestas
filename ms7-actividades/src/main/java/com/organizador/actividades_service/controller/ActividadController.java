package com.organizador.actividades_service.controller;

import com.organizador.actividades_service.dto.ActividadDTO;
import com.organizador.actividades_service.service.ActividadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/actividades")
public class ActividadController {

    @Autowired
    private ActividadService service;

    @GetMapping
    public ResponseEntity<List<ActividadDTO.Response>> listar() {
        List<ActividadDTO.Response> lista = service.getActividades();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getActividad(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ActividadDTO.Response> agregar(@Valid @RequestBody ActividadDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveActividad(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteActividad(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actividad no encontrada");
        }
    }
}
