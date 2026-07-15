package com.organizador.eventos_service;

import com.organizador.eventos_service.model.Evento;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventosTest {

    @Test
    void registrarEventoTest() {
        Evento evento = new Evento();
        Faker faker = new Faker();

        evento.setNombre(faker.lorem().sentence(3));
        evento.setDescripcion(faker.lorem().paragraph());
        evento.setFechaEvento(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));
        evento.setHoraInicio(LocalTime.of(faker.number().numberBetween(8, 22), 0));
        evento.setLugar(faker.address().fullAddress());
        evento.setAnfitrionId(faker.number().numberBetween(1, 1000));
        evento.setMaximoInvitados(faker.number().numberBetween(10, 500));
        evento.setEstado(Evento.EstadoEvento.ABIERTO);
        evento.setActivo(true);
        evento.setFechaRegistro(LocalDate.now());

        System.out.println(evento);

        assertNotNull(evento);
        assertEquals(Evento.EstadoEvento.ABIERTO, evento.getEstado());
        assertTrue(evento.isActivo());
    }
}
