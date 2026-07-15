package com.organizador.votaciones_service.service;

import com.organizador.votaciones_service.dto.VotacionDTO;
import com.organizador.votaciones_service.model.Votacion;
import com.organizador.votaciones_service.repository.VotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotacionService {

    @Autowired
    private VotacionRepository repository;

    public List<VotacionDTO.Response> getVotaciones() {
        return repository.findAll().stream()
                .filter(Votacion::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<VotacionDTO.Response> getVotacion(Integer id) {
        return repository.findById(id)
                .filter(Votacion::isActivo)
                .map(this::toResponse);
    }

    public VotacionDTO.Response saveVotacion(VotacionDTO.Request dto) {
        Votacion v = new Votacion();
        v.setEventoId(dto.getEventoId());
        v.setTema(dto.getTema());
        v.setEstado(Votacion.EstadoVotacion.ABIERTA);
        return toResponse(repository.save(v));
    }

    public void deleteVotacion(Integer id) {
        Votacion v = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Votacion no encontrada"));
        v.setActivo(false);
        repository.save(v);
    }

    public Optional<VotacionDTO.Response> updateVotacion(Integer id, VotacionDTO.Request dto) {
        return repository.findById(id).map(v -> {
            v.setTema(dto.getTema());
            v.setEstado(Votacion.EstadoVotacion.valueOf(dto.getEstado()));
            return toResponse(repository.save(v));
        });
    }

    private VotacionDTO.Response toResponse(Votacion v) {
        VotacionDTO.Response r = new VotacionDTO.Response();
        r.setId(v.getId());
        r.setEventoId(v.getEventoId());
        r.setTema(v.getTema());
        r.setEstado(v.getEstado().name());
        r.setFechaRegistro(v.getFechaRegistro());
        return r;
    }
}
