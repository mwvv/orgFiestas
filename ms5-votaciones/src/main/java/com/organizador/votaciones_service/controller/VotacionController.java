package com.organizador.votaciones_service.controller;

import com.organizador.votaciones_service.dto.VotacionDTO;
import com.organizador.votaciones_service.service.VotacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/votaciones")
public class VotacionController {

    @Autowired
    private VotacionService service;

    @GetMapping
    public ResponseEntity<List<VotacionDTO.Response>> listar() {
        List<VotacionDTO.Response> lista = service.getVotaciones();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotacionDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getVotacion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VotacionDTO.Response> agregar(@Valid @RequestBody VotacionDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveVotacion(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteVotacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Votacion no encontrada");
        }
    }
}
