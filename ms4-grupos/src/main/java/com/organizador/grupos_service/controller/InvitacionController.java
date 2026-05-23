package com.organizador.grupos_service.controller;

import com.organizador.grupos_service.dto.InvitacionDTO;
import com.organizador.grupos_service.service.InvitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/invitaciones")
public class InvitacionController {

    @Autowired
    private InvitacionService service;

    @GetMapping
    public ResponseEntity<List<InvitacionDTO.Response>> listar() {
        List<InvitacionDTO.Response> lista = service.getInvitaciones();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvitacionDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getInvitacion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InvitacionDTO.Response> agregar(@Valid @RequestBody InvitacionDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveInvitacion(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteInvitacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invitacion no encontrada");
        }
    }
}
