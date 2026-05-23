package com.organizador.grupos_service.repository;

import com.organizador.grupos_service.model.GrupoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GrupoEventoRepository extends JpaRepository<GrupoEvento, Integer> {
    List<GrupoEvento> findByEventoId(Integer eventoId);
}
