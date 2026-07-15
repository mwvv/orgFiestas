package com.organizador.actividades_service.repository;

import com.organizador.actividades_service.model.ActividadEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActividadEventoRepository extends JpaRepository<ActividadEvento, Integer> {
    List<ActividadEvento> findByEventoId(Integer eventoId);
}
