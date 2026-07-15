package com.organizador.ruleta_service;

import com.organizador.ruleta_service.model.RuletaConsumible;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class  RuletaTest {

    @Test
    void registrarRuletaConsumibleTest() {
        RuletaConsumible ruleta = new RuletaConsumible();
        Faker faker = new Faker();

        ruleta.setEventoId(faker.number().numberBetween(1, 1000));
        ruleta.setConsumibleId(faker.number().numberBetween(1, 1000));
        ruleta.setUsuarioAsignadoId(faker.number().numberBetween(1, 1000));
        ruleta.setEstado(RuletaConsumible.EstadoRuleta.SORTEADO);
        ruleta.setFechaSorteo(LocalDate.now());
        ruleta.setActivo(true);
        ruleta.setFechaRegistro(LocalDate.now());

        System.out.println(ruleta);

        assertNotNull(ruleta);
        assertEquals(RuletaConsumible.EstadoRuleta.SORTEADO, ruleta.getEstado());
        assertTrue(ruleta.isActivo());
    }
}
