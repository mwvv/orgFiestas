package com.organizador.social_service.service;

import com.organizador.social_service.client.UsuarioClient;
import com.organizador.social_service.dto.SolicitudDTO;
import com.organizador.social_service.model.SolicitudAmistad;
import com.organizador.social_service.repository.SolicitudAmistadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudAmistadService {

    @Autowired
    private SolicitudAmistadRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<SolicitudDTO.Response> getSolicitudes() {
        return repository.findAll().stream()
                .filter(SolicitudAmistad::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<SolicitudDTO.Response> getSolicitud(Integer id) {
        return repository.findById(id)
                .filter(SolicitudAmistad::isActivo)
                .map(this::toResponse);
    }

    public SolicitudDTO.Response saveSolicitud(SolicitudDTO.Request dto) {
        SolicitudAmistad s = new SolicitudAmistad();
        s.setUsuarioEmisorId(dto.getUsuarioEmisorId());
        s.setUsuarioReceptorId(dto.getUsuarioReceptorId());
        s.setMensaje(dto.getMensaje());
        s.setEstado(SolicitudAmistad.EstadoSolicitud.PENDIENTE);
        return toResponse(repository.save(s));
    }

    public void deleteSolicitud(Integer id) {
        SolicitudAmistad s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        s.setActivo(false);
        repository.save(s);
    }


    public Optional<SolicitudDTO.Response> updateSolicitud(Integer id, SolicitudDTO.Request dto) {
        return repository.findById(id).map(s -> {
            s.setUsuarioEmisorId(dto.getUsuarioEmisorId());
            s.setUsuarioReceptorId(dto.getUsuarioReceptorId());
            s.setMensaje(dto.getMensaje());
            s.setEstado(SolicitudAmistad.EstadoSolicitud.valueOf(dto.getEstado())); //
            return toResponse(repository.save(s));
        });
    }


    private SolicitudDTO.Response toResponse(SolicitudAmistad s) {
        SolicitudDTO.Response r = new SolicitudDTO.Response();
        r.setId(s.getId());
        r.setUsuarioEmisorId(s.getUsuarioEmisorId());
        r.setNombreEmisor(usuarioClient.obtenerNombreUsuario(s.getUsuarioEmisorId()));
        r.setUsuarioReceptorId(s.getUsuarioReceptorId());
        r.setEstado(s.getEstado().name());
        r.setMensaje(s.getMensaje());
        r.setFechaRegistro(s.getFechaRegistro());
        return r;
    }
}
