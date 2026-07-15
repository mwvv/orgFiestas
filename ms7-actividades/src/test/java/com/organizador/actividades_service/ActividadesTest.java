package com.organizador.actividades_service;

import com.organizador.actividades_service.model.Actividad;
import com.organizador.actividades_service.model.ActividadEvento;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActividadesTest {

    @Test
    void registrarActividadTest() {
        Actividad actividad = new Actividad();
        Faker faker = new Faker();

        actividad.setNombre(faker.lorem().sentence(3));
        actividad.setDescripcion(faker.lorem().paragraph());
        actividad.setDuracionMinutos(faker.number().numberBetween(10, 120));
        actividad.setTematicaId(faker.number().numberBetween(1, 1000));
        actividad.setTipo(Actividad.TipoActividad.JUEGO);
        actividad.setActivo(true);
        actividad.setFechaRegistro(LocalDate.now());

        System.out.println(actividad);

        assertNotNull(actividad);
        assertEquals(Actividad.TipoActividad.JUEGO, actividad.getTipo());
        assertTrue(actividad.isActivo());
    }

    @Test
    void registrarActividadEventoTest() {
        ActividadEvento actividadEvento = new ActividadEvento();
        Faker faker = new Faker();

        actividadEvento.setActividadId(faker.number().numberBetween(1, 1000));
        actividadEvento.setEventoId(faker.number().numberBetween(1, 1000));
        actividadEvento.setHoraInicio(LocalTime.of(faker.number().numberBetween(8, 22), 0));
        actividadEvento.setEstado(ActividadEvento.EstadoActividad.PENDIENTE);
        actividadEvento.setActivo(true);
        actividadEvento.setFechaRegistro(LocalDate.now());

        System.out.println(actividadEvento);

        assertNotNull(actividadEvento);
        assertEquals(ActividadEvento.EstadoActividad.PENDIENTE, actividadEvento.getEstado());
        assertTrue(actividadEvento.isActivo());
    }
}
