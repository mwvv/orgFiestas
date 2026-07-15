package com.organizador.social_service.repository;

import com.organizador.social_service.model.SolicitudAmistad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitudAmistadRepository extends JpaRepository<SolicitudAmistad, Integer> {
    List<SolicitudAmistad> findByUsuarioReceptorId(Integer receptorId);
    List<SolicitudAmistad> findByUsuarioEmisorId(Integer emisorId);
}
