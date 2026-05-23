package com.organizador.consumibles_service.repository;

import com.organizador.consumibles_service.model.Consumible;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsumibleRepository extends JpaRepository<Consumible, Integer> {
    List<Consumible> findByCategoria(Consumible.CategoriaConsumible categoria);
}
