package com.organizador.usuarios_service.service;

import com.organizador.usuarios_service.dto.UsuarioDTO;
import com.organizador.usuarios_service.model.Usuario;
import com.organizador.usuarios_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO.Response> getUsuarios() {
        return repository.findAll().stream()
                .filter(Usuario::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO.Response> getUsuario(Integer id) {
        return repository.findById(id)
                .filter(Usuario::isActivo)
                .map(this::toResponse);
    }

    public UsuarioDTO.Response saveUsuario(UsuarioDTO.Request dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena());
        usuario.setFotoPerfil(dto.getFotoPerfil());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);
        return toResponse(repository.save(usuario));
    }

    public Optional<UsuarioDTO.Response> updateUsuario(Integer id, UsuarioDTO.Request dto) {
        return repository.findById(id).map(usuario -> {
            usuario.setNombre(dto.getNombre());
            usuario.setEmail(dto.getEmail());
            usuario.setFotoPerfil(dto.getFotoPerfil());
            usuario.setFechaNacimiento(dto.getFechaNacimiento());
            return toResponse(repository.save(usuario));
        });
    }

    public void deleteUsuario(Integer id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(false);
        repository.save(usuario);
    }

    private UsuarioDTO.Response toResponse(Usuario u) {
        UsuarioDTO.Response r = new UsuarioDTO.Response();
        r.setId(u.getId());
        r.setNombre(u.getNombre());
        r.setEmail(u.getEmail());
        r.setFotoPerfil(u.getFotoPerfil());
        r.setFechaNacimiento(u.getFechaNacimiento());
        r.setEstado(u.getEstado().name());
        r.setFechaRegistro(u.getFechaRegistro());
        return r;
    }
}
