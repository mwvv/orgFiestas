package com.organizador.consumibles_service.controller;

import com.organizador.consumibles_service.dto.ConsumibleDTO;
import com.organizador.consumibles_service.service.ConsumibleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/consumibles")
public class ConsumibleController {

    @Autowired
    private ConsumibleService service;

    @GetMapping
    public ResponseEntity<List<ConsumibleDTO.Response>> listar() {
        List<ConsumibleDTO.Response> lista = service.getConsumibles();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumibleDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getConsumible(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsumibleDTO.Response> agregar(@Valid @RequestBody ConsumibleDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveConsumible(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteConsumible(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumible no encontrado");
        }
    }
}
