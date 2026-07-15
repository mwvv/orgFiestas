package com.organizador.eventos_service.repository;

import com.organizador.eventos_service.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByAnfitrionId(Integer anfitrionId);
}
