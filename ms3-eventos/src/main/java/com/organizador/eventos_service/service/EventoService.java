package com.organizador.eventos_service.service;

import com.organizador.eventos_service.client.UsuarioClient;
import com.organizador.eventos_service.dto.EventoDTO;
import com.organizador.eventos_service.model.Evento;
import com.organizador.eventos_service.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<EventoDTO.Response> getEventos() {
        return repository.findAll().stream()
                .filter(Evento::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<EventoDTO.Response> getEvento(Integer id) {
        return repository.findById(id)
                .filter(Evento::isActivo)
                .map(this::toResponse);
    }

    public EventoDTO.Response saveEvento(EventoDTO.Request dto) {
        Evento e = new Evento();
        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setFechaEvento(dto.getFechaEvento());
        e.setHoraInicio(dto.getHoraInicio());
        e.setLugar(dto.getLugar());
        e.setAnfitrionId(dto.getAnfitrionId());
        e.setMaximoInvitados(dto.getMaximoInvitados());
        e.setEstado(Evento.EstadoEvento.ABIERTO);
        return toResponse(repository.save(e));
    }

    public Optional<EventoDTO.Response> updateEvento(Integer id, EventoDTO.Request dto) {
        return repository.findById(id).map(e -> {
            e.setNombre(dto.getNombre());
            e.setDescripcion(dto.getDescripcion());
            e.setFechaEvento(dto.getFechaEvento());
            e.setHoraInicio(dto.getHoraInicio());
            e.setLugar(dto.getLugar());
            e.setMaximoInvitados(dto.getMaximoInvitados());
            return toResponse(repository.save(e));
        });
    }

    public void deleteEvento(Integer id) {
        Evento e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        e.setActivo(false);
        repository.save(e);
    }

    private EventoDTO.Response toResponse(Evento e) {
        EventoDTO.Response r = new EventoDTO.Response();
        r.setId(e.getId());
        r.setNombre(e.getNombre());
        r.setDescripcion(e.getDescripcion());
        r.setFechaEvento(e.getFechaEvento());
        r.setHoraInicio(e.getHoraInicio());
        r.setLugar(e.getLugar());
        r.setAnfitrionId(e.getAnfitrionId());
        r.setNombreAnfitrion(usuarioClient.obtenerNombreUsuario(e.getAnfitrionId()));
        r.setMaximoInvitados(e.getMaximoInvitados());
        r.setEstado(e.getEstado().name());
        r.setFechaRegistro(e.getFechaRegistro());
        return r;
    }
}
