package com.organizador.actividades_service.service;

import com.organizador.actividades_service.client.TematicaClient;
import com.organizador.actividades_service.dto.ActividadDTO;
import com.organizador.actividades_service.model.Actividad;
import com.organizador.actividades_service.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository repository;

    @Autowired
    private TematicaClient tematicaClient;

    public List<ActividadDTO.Response> getActividades() {
        return repository.findAll().stream()
                .filter(Actividad::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<ActividadDTO.Response> getActividad(Integer id) {
        return repository.findById(id)
                .filter(Actividad::isActivo)
                .map(this::toResponse);
    }

    public ActividadDTO.Response saveActividad(ActividadDTO.Request dto) {
        Actividad a = new Actividad();
        a.setNombre(dto.getNombre());
        a.setDescripcion(dto.getDescripcion());
        a.setDuracionMinutos(dto.getDuracionMinutos());
        a.setTematicaId(dto.getTematicaId());
        a.setTipo(Actividad.TipoActividad.valueOf(dto.getTipo()));
        return toResponse(repository.save(a));
    }

    public void deleteActividad(Integer id) {
        Actividad a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        a.setActivo(false);
        repository.save(a);
    }

    private ActividadDTO.Response toResponse(Actividad a) {
        ActividadDTO.Response r = new ActividadDTO.Response();
        r.setId(a.getId());
        r.setNombre(a.getNombre());
        r.setDescripcion(a.getDescripcion());
        r.setDuracionMinutos(a.getDuracionMinutos());
        r.setTematicaId(a.getTematicaId());
        r.setTipo(a.getTipo().name());
        r.setFechaRegistro(a.getFechaRegistro());
        return r;
    }
}
