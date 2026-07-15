package com.organizador.actividades_service.repository;

import com.organizador.actividades_service.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByTematicaId(Integer tematicaId);
}
