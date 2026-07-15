package com.organizador.ruleta_service.service;

import com.organizador.ruleta_service.client.ConsumibleClient;
import com.organizador.ruleta_service.client.UsuarioClient;
import com.organizador.ruleta_service.dto.RuletaDTO;
import com.organizador.ruleta_service.model.RuletaConsumible;
import com.organizador.ruleta_service.repository.RuletaConsumibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuletaConsumibleService {

    @Autowired
    private RuletaConsumibleRepository repository;

    @Autowired
    private ConsumibleClient consumibleClient;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<RuletaDTO.Response> getRuletas() {
        return repository.findAll().stream()
                .filter(RuletaConsumible::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<RuletaDTO.Response> getRuleta(Integer id) {
        return repository.findById(id)
                .filter(RuletaConsumible::isActivo)
                .map(this::toResponse);
    }

    public RuletaDTO.Response saveRuleta(RuletaDTO.Request dto) {
        RuletaConsumible r = new RuletaConsumible();
        r.setEventoId(dto.getEventoId());
        r.setConsumibleId(dto.getConsumibleId());
        r.setUsuarioAsignadoId(dto.getUsuarioAsignadoId());
        r.setEstado(RuletaConsumible.EstadoRuleta.SORTEADO);
        return toResponse(repository.save(r));
    }

    public Optional<RuletaDTO.Response> updateRuleta(Integer id, RuletaDTO.Request dto){
        return repository.findById(id).map(r ->{
            r.setConsumibleId(dto.getConsumibleId());
            r.setUsuarioAsignadoId(dto.getUsuarioAsignadoId());
            r.setEstado(RuletaConsumible.EstadoRuleta.valueOf(dto.getEstado()));
            return toResponse(repository.save(r));
        });
    }


    public void deleteRuleta(Integer id) {
        RuletaConsumible r = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruleta no encontrada"));
        r.setActivo(false);
        repository.save(r);
    }

    private RuletaDTO.Response toResponse(RuletaConsumible r) {
        RuletaDTO.Response res = new RuletaDTO.Response();
        res.setId(r.getId());
        res.setEventoId(r.getEventoId());
        res.setConsumibleId(r.getConsumibleId());
        res.setNombreConsumible(consumibleClient.obtenerNombreConsumible(r.getConsumibleId()));
        res.setUsuarioAsignadoId(r.getUsuarioAsignadoId());
        res.setNombreUsuario(usuarioClient.obtenerNombreUsuario(r.getUsuarioAsignadoId()));
        res.setEstado(r.getEstado().name());
        res.setFechaSorteo(r.getFechaSorteo());
        res.setFechaRegistro(r.getFechaRegistro());
        return res;
    }
}
