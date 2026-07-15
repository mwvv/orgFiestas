package com.organizador.social_service.repository;

import com.organizador.social_service.model.Amistad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AmistadRepository extends JpaRepository<Amistad, Integer> {
    List<Amistad> findByUsuarioId1OrUsuarioId2(Integer id1, Integer id2);
}
