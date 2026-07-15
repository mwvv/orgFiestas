package com.organizador.grupos_service.repository;

import com.organizador.grupos_service.model.Invitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvitacionRepository extends JpaRepository<Invitacion, Integer> {
    List<Invitacion> findByEventoId(Integer eventoId);
    List<Invitacion> findByUsuarioInvitadoId(Integer usuarioId);
}
