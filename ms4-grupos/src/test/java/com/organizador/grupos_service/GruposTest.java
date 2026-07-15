package com.organizador.grupos_service;

import com.organizador.grupos_service.model.GrupoEvento;
import com.organizador.grupos_service.model.Invitacion;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GruposTest {

    @Test
    void registrarGrupoEventoTest() {
        GrupoEvento grupoEvento = new GrupoEvento();
        Faker faker = new Faker();

        grupoEvento.setEventoId(faker.number().numberBetween(1, 1000));
        grupoEvento.setUsuarioId(faker.number().numberBetween(1, 1000));
        grupoEvento.setRol(GrupoEvento.RolGrupo.PARTICIPANTE);
        grupoEvento.setActivo(true);
        grupoEvento.setFechaRegistro(LocalDate.now());

        System.out.println(grupoEvento);

        assertNotNull(grupoEvento);
        assertEquals(GrupoEvento.RolGrupo.PARTICIPANTE, grupoEvento.getRol());
        assertTrue(grupoEvento.isActivo());
    }

    @Test
    void registrarInvitacionTest() {
        Invitacion invitacion = new Invitacion();
        Faker faker = new Faker();

        invitacion.setEventoId(faker.number().numberBetween(1, 1000));
        invitacion.setUsuarioInvitadoId(faker.number().numberBetween(1, 1000));
        invitacion.setUsuarioAnfitrionId(faker.number().numberBetween(1, 1000));
        invitacion.setEstado(Invitacion.EstadoInvitacion.PENDIENTE);
        invitacion.setMensaje(faker.lorem().sentence());
        invitacion.setActivo(true);
        invitacion.setFechaRegistro(LocalDate.now());

        System.out.println(invitacion);

        assertNotNull(invitacion);
        assertEquals(Invitacion.EstadoInvitacion.PENDIENTE, invitacion.getEstado());
        assertTrue(invitacion.isActivo());
    }
}
