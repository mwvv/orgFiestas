package com.organizador.votaciones_service;

import com.organizador.votaciones_service.model.OpcionVotacion;
import com.organizador.votaciones_service.model.Votacion;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VotacionesTest {

    @Test
    void registrarVotacionTest() {
        Votacion votacion = new Votacion();
        Faker faker = new Faker();

        votacion.setEventoId(faker.number().numberBetween(1, 1000));
        votacion.setTema(faker.lorem().sentence(4));
        votacion.setEstado(Votacion.EstadoVotacion.ABIERTA);
        votacion.setActivo(true);
        votacion.setFechaRegistro(LocalDate.now());

        System.out.println(votacion);

        assertNotNull(votacion);
        assertEquals(Votacion.EstadoVotacion.ABIERTA, votacion.getEstado());
        assertTrue(votacion.isActivo());
    }

    @Test
    void registrarOpcionVotacionTest() {
        OpcionVotacion opcion = new OpcionVotacion();
        Faker faker = new Faker();

        opcion.setVotacionId(faker.number().numberBetween(1, 1000));
        opcion.setDescripcion(faker.lorem().sentence(3));
        opcion.setTotalVotos(faker.number().numberBetween(0, 100));
        opcion.setActivo(true);
        opcion.setFechaRegistro(LocalDate.now());

        System.out.println(opcion);

        assertNotNull(opcion);
        assertEquals(opcion.getVotacionId(), opcion.getVotacionId());
        assertTrue(opcion.isActivo());
    }
}
