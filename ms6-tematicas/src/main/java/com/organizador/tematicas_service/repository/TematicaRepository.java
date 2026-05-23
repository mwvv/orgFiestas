package com.organizador.tematicas_service.repository;

import com.organizador.tematicas_service.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TematicaRepository extends JpaRepository<Tematica, Integer> {}
