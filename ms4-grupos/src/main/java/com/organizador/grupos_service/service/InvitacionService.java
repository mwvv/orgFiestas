package com.organizador.grupos_service.service;

import com.organizador.grupos_service.client.EventoClient;
import com.organizador.grupos_service.dto.InvitacionDTO;
import com.organizador.grupos_service.model.Invitacion;
import com.organizador.grupos_service.repository.InvitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvitacionService {

    @Autowired
    private InvitacionRepository repository;

    @Autowired
    private EventoClient eventoClient;

    public List<InvitacionDTO.Response> getInvitaciones() {
        return repository.findAll().stream()
                .filter(Invitacion::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<InvitacionDTO.Response> getInvitacion(Integer id) {
        return repository.findById(id)
                .filter(Invitacion::isActivo)
                .map(this::toResponse);
    }

    public InvitacionDTO.Response saveInvitacion(InvitacionDTO.Request dto) {
        Invitacion i = new Invitacion();
        i.setEventoId(dto.getEventoId());
        i.setUsuarioInvitadoId(dto.getUsuarioInvitadoId());
        i.setUsuarioAnfitrionId(dto.getUsuarioAnfitrionId());
        i.setMensaje(dto.getMensaje());
        i.setEstado(Invitacion.EstadoInvitacion.PENDIENTE);
        return toResponse(repository.save(i));
    }

    public void deleteInvitacion(Integer id) {
        Invitacion i = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invitacion no encontrada"));
        i.setActivo(false);
        repository.save(i);
    }

    private InvitacionDTO.Response toResponse(Invitacion i) {
        InvitacionDTO.Response r = new InvitacionDTO.Response();
        r.setId(i.getId());
        r.setEventoId(i.getEventoId());
        r.setNombreEvento(eventoClient.obtenerNombreEvento(i.getEventoId()));
        r.setUsuarioInvitadoId(i.getUsuarioInvitadoId());
        r.setUsuarioAnfitrionId(i.getUsuarioAnfitrionId());
        r.setEstado(i.getEstado().name());
        r.setMensaje(i.getMensaje());
        r.setFechaRegistro(i.getFechaRegistro());
        return r;
    }
}
