package com.organizador.eventos_service.controller;

import com.organizador.eventos_service.dto.EventoDTO;
import com.organizador.eventos_service.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @GetMapping
    public ResponseEntity<List<EventoDTO.Response>> listar() {
        List<EventoDTO.Response> lista = service.getEventos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getEvento(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventoDTO.Response> agregar(@Valid @RequestBody EventoDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveEvento(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO.Response> editar(@Valid @RequestBody EventoDTO.Request dto,
                                                      @PathVariable Integer id) {
        return service.updateEvento(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteEvento(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado");
        }
    }
}
