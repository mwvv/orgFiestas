package com.organizador.tematicas_service.repository;

import com.organizador.tematicas_service.model.VotoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VotoUsuarioRepository extends JpaRepository<VotoUsuario, Integer> {
    List<VotoUsuario> findByVotacionId(Integer votacionId);
    boolean existsByVotacionIdAndUsuarioId(Integer votacionId, Integer usuarioId);
}
