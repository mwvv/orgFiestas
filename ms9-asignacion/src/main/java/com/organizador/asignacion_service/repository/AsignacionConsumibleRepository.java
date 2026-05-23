package com.organizador.asignacion_service.repository;

import com.organizador.asignacion_service.model.AsignacionConsumible;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsignacionConsumibleRepository extends JpaRepository<AsignacionConsumible, Integer> {
    List<AsignacionConsumible> findByEventoId(Integer eventoId);
    List<AsignacionConsumible> findByUsuarioId(Integer usuarioId);
}
