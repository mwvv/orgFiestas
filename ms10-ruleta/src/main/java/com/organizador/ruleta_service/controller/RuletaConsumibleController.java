package com.organizador.ruleta_service.controller;

import com.organizador.ruleta_service.dto.RuletaDTO;
import com.organizador.ruleta_service.service.RuletaConsumibleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaConsumibleController {

    @Autowired
    private RuletaConsumibleService service;

    @GetMapping
    public ResponseEntity<List<RuletaDTO.Response>> listar() {
        List<RuletaDTO.Response> lista = service.getRuletas();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuletaDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getRuleta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RuletaDTO.Response> agregar(@Valid @RequestBody RuletaDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRuleta(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteRuleta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorteo no encontrado");
        }
    }
}
