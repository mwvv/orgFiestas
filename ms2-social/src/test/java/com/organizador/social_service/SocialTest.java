package com.organizador.social_service;

import com.organizador.social_service.model.Amistad;
import com.organizador.social_service.model.SolicitudAmistad;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SocialTest {

    @Test
    void registrarAmistadTest() {
        Amistad amistad = new Amistad();
        Faker faker = new Faker();

        amistad.setUsuarioId1(faker.number().numberBetween(1, 1000));
        amistad.setUsuarioId2(faker.number().numberBetween(1, 1000));
        amistad.setEstado(Amistad.EstadoAmistad.PENDIENTE);
        amistad.setFechaAmistad(LocalDate.now());
        amistad.setActivo(true);
        amistad.setFechaRegistro(LocalDate.now());

        System.out.println(amistad);

        assertNotNull(amistad);
        assertEquals(Amistad.EstadoAmistad.PENDIENTE, amistad.getEstado());
        assertTrue(amistad.isActivo());
    }

    @Test
    void registrarSolicitudAmistadTest() {
        SolicitudAmistad solicitud = new SolicitudAmistad();
        Faker faker = new Faker();

        solicitud.setUsuarioEmisorId(faker.number().numberBetween(1, 1000));
        solicitud.setUsuarioReceptorId(faker.number().numberBetween(1, 1000));
        solicitud.setEstado(SolicitudAmistad.EstadoSolicitud.PENDIENTE);
        solicitud.setMensaje(faker.lorem().sentence());
        solicitud.setActivo(true);
        solicitud.setFechaRegistro(LocalDate.now());

        System.out.println(solicitud);

        assertNotNull(solicitud);
        assertEquals(SolicitudAmistad.EstadoSolicitud.PENDIENTE, solicitud.getEstado());
        assertTrue(solicitud.isActivo());
    }
}
