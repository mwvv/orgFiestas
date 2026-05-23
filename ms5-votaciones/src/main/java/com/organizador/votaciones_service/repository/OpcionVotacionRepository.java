package com.organizador.votaciones_service.repository;

import com.organizador.votaciones_service.model.OpcionVotacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OpcionVotacionRepository extends JpaRepository<OpcionVotacion, Integer> {
    List<OpcionVotacion> findByVotacionId(Integer votacionId);
}
