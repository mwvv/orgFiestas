package com.organizador.asignacion_service;

import com.organizador.asignacion_service.model.AsignacionConsumible;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsignacionTest {

    @Test
    void registrarAsignacionConsumibleTest() {
        AsignacionConsumible asignacion = new AsignacionConsumible();
        Faker faker = new Faker();

        asignacion.setEventoId(faker.number().numberBetween(1, 1000));
        asignacion.setUsuarioId(faker.number().numberBetween(1, 1000));
        asignacion.setConsumibleId(faker.number().numberBetween(1, 1000));
        asignacion.setCantidad(faker.number().numberBetween(1, 50));
        asignacion.setEstado(AsignacionConsumible.EstadoAsignacion.PENDIENTE);
        asignacion.setActivo(true);
        asignacion.setFechaRegistro(LocalDate.now());

        System.out.println(asignacion);

        assertNotNull(asignacion);
        assertEquals(AsignacionConsumible.EstadoAsignacion.PENDIENTE, asignacion.getEstado());
        assertTrue(asignacion.isActivo());
    }
}
