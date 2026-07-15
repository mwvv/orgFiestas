package com.organizador.tematicas_service;

import com.organizador.tematicas_service.model.Tematica;
import com.organizador.tematicas_service.model.VotoUsuario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TematicasTest {

    @Test
    void registrarTematicaTest() {
        Tematica tematica = new Tematica();
        Faker faker = new Faker();

        tematica.setNombre(faker.lorem().word());
        tematica.setDescripcion(faker.lorem().paragraph());
        tematica.setImagen(faker.internet().url());
        tematica.setActivo(true);
        tematica.setFechaRegistro(LocalDate.now());

        System.out.println(tematica);

        assertNotNull(tematica);
        assertNotNull(tematica.getNombre());
        assertTrue(tematica.isActivo());
    }

    @Test
    void registrarVotoUsuarioTest() {
        VotoUsuario voto = new VotoUsuario();
        Faker faker = new Faker();

        voto.setVotacionId(faker.number().numberBetween(1, 1000));
        voto.setOpcionId(faker.number().numberBetween(1, 1000));
        voto.setUsuarioId(faker.number().numberBetween(1, 1000));
        voto.setActivo(true);
        voto.setFechaRegistro(LocalDate.now());

        System.out.println(voto);

        assertNotNull(voto);
        assertNotNull(voto.getVotacionId());
        assertTrue(voto.isActivo());
    }
}
