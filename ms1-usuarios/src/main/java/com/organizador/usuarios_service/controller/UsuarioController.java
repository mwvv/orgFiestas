package com.organizador.usuarios_service.controller;

import com.organizador.usuarios_service.dto.UsuarioDTO;
import com.organizador.usuarios_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO.Response>> listar() {
        List<UsuarioDTO.Response> usuarios = service.getUsuarios();
        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Response> buscarPorId(@PathVariable Integer id) {
        return service.getUsuario(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO.Response> agregar(@Valid @RequestBody UsuarioDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUsuario(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Response> editar(@Valid @RequestBody UsuarioDTO.Request dto,
                                                       @PathVariable Integer id) {
        return service.updateUsuario(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
