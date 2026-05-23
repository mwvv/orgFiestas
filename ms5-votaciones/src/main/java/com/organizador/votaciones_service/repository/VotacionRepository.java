package com.organizador.votaciones_service.repository;

import com.organizador.votaciones_service.model.Votacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VotacionRepository extends JpaRepository<Votacion, Integer> {
    List<Votacion> findByEventoId(Integer eventoId);
}
