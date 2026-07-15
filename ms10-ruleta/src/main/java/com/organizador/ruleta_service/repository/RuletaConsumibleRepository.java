package com.organizador.ruleta_service.repository;

import com.organizador.ruleta_service.model.RuletaConsumible;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RuletaConsumibleRepository extends JpaRepository<RuletaConsumible, Integer> {
    List<RuletaConsumible> findByEventoId(Integer eventoId);
}
