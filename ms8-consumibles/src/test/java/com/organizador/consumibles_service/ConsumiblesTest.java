package com.organizador.consumibles_service;

import com.organizador.consumibles_service.model.Consumible;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsumiblesTest {

    @Test
    void registrarConsumibleTest() {
        Consumible consumible = new Consumible();
        Faker faker = new Faker();

        consumible.setNombre(faker.food().dish());
        consumible.setDescripcion(faker.lorem().sentence());
        consumible.setCategoria(Consumible.CategoriaConsumible.BEBIDA);
        consumible.setPrecio(faker.number().randomDouble(2, 1, 100));
        consumible.setActivo(true);
        consumible.setFechaRegistro(LocalDate.now());

        System.out.println(consumible);

        assertNotNull(consumible);
        assertEquals(Consumible.CategoriaConsumible.BEBIDA, consumible.getCategoria());
        assertTrue(consumible.isActivo());
    }
}
