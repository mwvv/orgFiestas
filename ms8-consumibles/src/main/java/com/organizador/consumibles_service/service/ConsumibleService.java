package com.organizador.consumibles_service.service;

import com.organizador.consumibles_service.dto.ConsumibleDTO;
import com.organizador.consumibles_service.model.Consumible;
import com.organizador.consumibles_service.repository.ConsumibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumibleService {

    @Autowired
    private ConsumibleRepository repository;

    public List<ConsumibleDTO.Response> getConsumibles() {
        return repository.findAll().stream()
                .filter(Consumible::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<ConsumibleDTO.Response> getConsumible(Integer id) {
        return repository.findById(id)
                .filter(Consumible::isActivo)
                .map(this::toResponse);
    }

    public ConsumibleDTO.Response saveConsumible(ConsumibleDTO.Request dto) {
        Consumible c = new Consumible();
        c.setNombre(dto.getNombre());
        c.setDescripcion(dto.getDescripcion());
        c.setCategoria(Consumible.CategoriaConsumible.valueOf(dto.getCategoria()));
        c.setPrecio(dto.getPrecio());
        return toResponse(repository.save(c));
    }

    public void deleteConsumible(Integer id) {
        Consumible c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumible no encontrado"));
        c.setActivo(false);
        repository.save(c);
    }

    public Optional<ConsumibleDTO.Response> updateConsumible(Integer id, ConsumibleDTO.Request dto){
        return repository.findById(id).map(c ->{
            c.setNombre(dto.getNombre());
            c.setDescripcion(dto.getDescripcion());
            c.setCategoria(Consumible.CategoriaConsumible.valueOf(dto.getCategoria()));
            c.setPrecio(dto.getPrecio());
            return toResponse(repository.save(c));
        });
    }


    private ConsumibleDTO.Response toResponse(Consumible c) {
        ConsumibleDTO.Response r = new ConsumibleDTO.Response();
        r.setId(c.getId());
        r.setNombre(c.getNombre());
        r.setDescripcion(c.getDescripcion());
        r.setCategoria(c.getCategoria().name());
        r.setPrecio(c.getPrecio());
        r.setFechaRegistro(c.getFechaRegistro());
        return r;
    }
}
