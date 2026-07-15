package com.organizador.usuarios_service;

import com.organizador.usuarios_service.model.Usuario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioTest {

    @Test
    void registrarUsuarioTest(){
        Usuario usuario = new Usuario();
        Faker faker = new Faker();

        usuario.setNombre(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setContrasena(faker.internet().password(6,20));
        usuario.setFotoPerfil(faker.internet().url());
        usuario.setFechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18,30)));
        usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);

        System.out.println(usuario);

        assertNotNull(usuario);
        assertEquals(Usuario.EstadoUsuario.ACTIVO, usuario.getEstado());

    }
}
