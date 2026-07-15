package com.organizador.tematicas_service.service;

import com.organizador.tematicas_service.dto.TematicaDTO;
import com.organizador.tematicas_service.model.Tematica;
import com.organizador.tematicas_service.repository.TematicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TematicaService {

    @Autowired
    private TematicaRepository repository;

    public List<TematicaDTO.Response> getTematicas() {
        return repository.findAll().stream()
                .filter(Tematica::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<TematicaDTO.Response> getTematica(Integer id) {
        return repository.findById(id)
                .filter(Tematica::isActivo)
                .map(this::toResponse);
    }

    public TematicaDTO.Response saveTematica(TematicaDTO.Request dto) {
        Tematica t = new Tematica();
        t.setNombre(dto.getNombre());
        t.setDescripcion(dto.getDescripcion());
        t.setImagen(dto.getImagen());
        return toResponse(repository.save(t));
    }

    public void deleteTematica(Integer id) {
        Tematica t = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tematica no encontrada"));
        t.setActivo(false);
        repository.save(t);
    }

    public Optional<TematicaDTO.Response> updateTematica(Integer id,TematicaDTO.Request dto){
        return repository.findById(id).map(t ->{
            t.setNombre(dto.getNombre());
            t.setDescripcion(dto.getDescripcion());
            t.setImagen(dto.getImagen());
            return toResponse((repository.save(t)));
        });
    }


    private TematicaDTO.Response toResponse(Tematica t) {
        TematicaDTO.Response r = new TematicaDTO.Response();
        r.setId(t.getId());
        r.setNombre(t.getNombre());
        r.setDescripcion(t.getDescripcion());
        r.setImagen(t.getImagen());
        r.setFechaRegistro(t.getFechaRegistro());
        return r;
    }
}
