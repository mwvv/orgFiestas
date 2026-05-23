package com.organizador.tematicas_service.controller;

import com.organizador.tematicas_service.dto.TematicaDTO;
import com.organizador.tematicas_service.service.TematicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tematicas")
public class TematicaController {

    @Autowired
    private TematicaService service;

    @GetMapping
    public ResponseEntity<List<TematicaDTO.Response>> listar() {
        List<TematicaDTO.Response> lista = service.getTematicas();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TematicaDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getTematica(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TematicaDTO.Response> agregar(@Valid @RequestBody TematicaDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTematica(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteTematica(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tematica no encontrada");
        }
    }
}
