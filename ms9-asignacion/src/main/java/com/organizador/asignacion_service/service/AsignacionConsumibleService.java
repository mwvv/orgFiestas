package com.organizador.asignacion_service.service;

import com.organizador.asignacion_service.client.ConsumibleClient;
import com.organizador.asignacion_service.dto.AsignacionDTO;
import com.organizador.asignacion_service.model.AsignacionConsumible;
import com.organizador.asignacion_service.repository.AsignacionConsumibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignacionConsumibleService {

    @Autowired
    private AsignacionConsumibleRepository repository;

    @Autowired
    private ConsumibleClient consumibleClient;

    public List<AsignacionDTO.Response> getAsignaciones() {
        return repository.findAll().stream()
                .filter(AsignacionConsumible::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<AsignacionDTO.Response> getAsignacion(Integer id) {
        return repository.findById(id)
                .filter(AsignacionConsumible::isActivo)
                .map(this::toResponse);
    }

    public AsignacionDTO.Response saveAsignacion(AsignacionDTO.Request dto) {
        AsignacionConsumible a = new AsignacionConsumible();
        a.setEventoId(dto.getEventoId());
        a.setUsuarioId(dto.getUsuarioId());
        a.setConsumibleId(dto.getConsumibleId());
        a.setCantidad(dto.getCantidad());
        a.setEstado(AsignacionConsumible.EstadoAsignacion.PENDIENTE);
        return toResponse(repository.save(a));
    }

    public void deleteAsignacion(Integer id) {
        AsignacionConsumible a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignacion no encontrada"));
        a.setActivo(false);
        repository.save(a);
    }

    private AsignacionDTO.Response toResponse(AsignacionConsumible a) {
        AsignacionDTO.Response r = new AsignacionDTO.Response();
        r.setId(a.getId());
        r.setEventoId(a.getEventoId());
        r.setUsuarioId(a.getUsuarioId());
        r.setConsumibleId(a.getConsumibleId());
        r.setNombreConsumible(consumibleClient.obtenerNombreConsumible(a.getConsumibleId()));
        r.setCantidad(a.getCantidad());
        r.setEstado(a.getEstado().name());
        r.setFechaRegistro(a.getFechaRegistro());
        return r;
    }
}
