# Plan de Pruebas - orgFiestas

## Objetivo

Validar el correcto funcionamiento de los microservicios del proyecto orgFiestas mediante pruebas unitarias utilizando JUnit y generación de datos ficticios con DataFaker.

---

## Herramientas

- Java 21
- Spring Boot
- JUnit 5
- DataFaker

---

# Casos de Prueba

# CP-01: Registrar Usuario

## Microservicio

ms1-usuarios

## Método

Creación de objeto `Usuario`

## Objetivo

Verificar que un usuario pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto Usuario debe existir.
* El estado del usuario debe corresponder al valor asignado (ACTIVO).

---

# CP-02: Registrar Amistad

## Microservicio

ms2-social

## Método

Creación de objeto `Amistad`

## Objetivo

Verificar que una amistad pueda crearse correctamente entre dos usuarios.

## Resultado Esperado

* El objeto Amistad debe existir.
* El estado de la amistad debe corresponder al valor asignado (PENDIENTE).
* El campo `activo` debe ser `true`.

---

# CP-03: Registrar Solicitud de Amistad

## Microservicio

ms2-social

## Método

Creación de objeto `SolicitudAmistad`

## Objetivo

Verificar que una solicitud de amistad pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto SolicitudAmistad debe existir.
* El estado de la solicitud debe corresponder al valor asignado (PENDIENTE).
* El campo `activo` debe ser `true`.

---

# CP-04: Registrar Evento

## Microservicio

ms3-eventos

## Método

Creación de objeto `Evento`

## Objetivo

Verificar que un evento pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto Evento debe existir.
* El estado del evento debe corresponder al valor asignado (ABIERTO).
* El campo `activo` debe ser `true`.

---

# CP-05: Registrar Grupo de Evento

## Microservicio

ms4-grupos

## Método

Creación de objeto `GrupoEvento`

## Objetivo

Verificar que un grupo asociado a un evento pueda crearse correctamente.

## Resultado Esperado

* El objeto GrupoEvento debe existir.
* El rol del grupo (campo `rol`, no `estado`) debe corresponder al valor asignado (PARTICIPANTE).
* El campo `activo` debe ser `true`.

---

# CP-06: Registrar Invitación

## Microservicio

ms4-grupos

## Método

Creación de objeto `Invitacion`

## Objetivo

Verificar que una invitación pueda crearse correctamente asociada a un evento.

## Resultado Esperado

* El objeto Invitacion debe existir.
* La invitación se asocia a un evento, un usuario invitado y un usuario anfitrión (`eventoId`, `usuarioInvitadoId`, `usuarioAnfitrionId`), no a un grupo.
* El estado de la invitación debe corresponder al valor asignado (PENDIENTE).
* El campo `activo` debe ser `true`.

---

# CP-07: Registrar Votación

## Microservicio

ms5-votaciones

## Método

Creación de objeto `Votacion`

## Objetivo

Verificar que una votación pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto Votacion debe existir.
* El estado de la votación debe corresponder al valor asignado (ABIERTA).
* El campo `activo` debe ser `true`.

---

# CP-08: Registrar Opción de Votación

## Microservicio

ms5-votaciones

## Método

Creación de objeto `OpcionVotacion`

## Objetivo

Verificar que una opción de votación pueda crearse correctamente.

## Resultado Esperado

* El objeto OpcionVotacion debe existir.
* El campo `activo` debe ser `true`.

---

# CP-09: Registrar Temática

## Microservicio

ms6-tematicas

## Método

Creación de objeto `Tematica`

## Objetivo

Verificar que una temática pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto Tematica debe existir.
* El nombre de la temática no debe ser nulo.
* El campo `activo` debe ser `true`.

---

# CP-10: Registrar Voto de Usuario

## Microservicio

ms6-tematicas

## Método

Creación de objeto `VotoUsuario`

## Objetivo

Verificar que el voto de un usuario pueda asociarse correctamente a una votación y una opción.

## Resultado Esperado

* El objeto VotoUsuario debe existir.
* El voto se asocia a una votación, una opción y un usuario (`votacionId`, `opcionId`, `usuarioId`), no directamente a una temática.
* El identificador de la votación (`votacionId`) no debe ser nulo.
* El campo `activo` debe ser `true`.

---

# CP-11: Registrar Actividad

## Microservicio

ms7-actividades

## Método

Creación de objeto `Actividad`

## Objetivo

Verificar que una actividad pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto Actividad debe existir.
* El tipo de la actividad (campo `tipo`, no `estado`) debe corresponder al valor asignado (JUEGO).
* El campo `activo` debe ser `true`.

---

# CP-12: Registrar Actividad de Evento

## Microservicio

ms7-actividades

## Método

Creación de objeto `ActividadEvento`

## Objetivo

Verificar que una actividad pueda asociarse correctamente a un evento.

## Resultado Esperado

* El objeto ActividadEvento debe existir.
* El estado de la actividad de evento debe corresponder al valor asignado (PENDIENTE).
* El campo `activo` debe ser `true`.

---

# CP-13: Registrar Consumible

## Microservicio

ms8-consumibles

## Método

Creación de objeto `Consumible`

## Objetivo

Verificar que un consumible pueda crearse correctamente con información válida.

## Resultado Esperado

* El objeto Consumible debe existir.
* La categoría del consumible (campo `categoria`, no `estado`) debe corresponder al valor asignado (BEBIDA).
* El campo `activo` debe ser `true`.

---

# CP-14: Registrar Asignación de Consumible

## Microservicio

ms9-asignacion

## Método

Creación de objeto `AsignacionConsumible`

## Objetivo

Verificar que un consumible pueda asignarse correctamente a un usuario dentro de un evento.

## Resultado Esperado

* El objeto AsignacionConsumible debe existir.
* El estado de la asignación debe corresponder al valor asignado (PENDIENTE).
* El campo `activo` debe ser `true`.

---

# CP-15: Registrar Ruleta de Consumible

## Microservicio

ms10-ruleta

## Método

Creación de objeto `RuletaConsumible`

## Objetivo

Verificar que el resultado de una ruleta de consumibles pueda registrarse correctamente.

## Resultado Esperado

* El objeto RuletaConsumible debe existir.
* El estado del resultado debe corresponder al valor asignado (SORTEADO).
* El campo `activo` debe ser `true`.

---

## Casos de Prueba

| ID    | Caso de Prueba                     | Microservicio    | Objetivo                                                              | Resultado Esperado                                                          | Tipo     |
|-------|-------------------------------------|-------------------|-------------------------------------------------------------------------|--------------------------------------------------------------------------------|----------|
| CP-01 | Registrar Usuario                   | ms1-usuarios      | Validar la creación de un usuario con información válida.               | El usuario existe y su estado corresponde al valor asignado (ACTIVO).          | Unitaria |
| CP-02 | Registrar Amistad                   | ms2-social        | Validar la creación de una amistad entre dos usuarios.                  | La amistad existe y su estado corresponde al valor asignado (PENDIENTE).       | Unitaria |
| CP-03 | Registrar Solicitud de Amistad      | ms2-social        | Validar la creación de una solicitud de amistad.                        | La solicitud existe y su estado corresponde al valor asignado (PENDIENTE).     | Unitaria |
| CP-04 | Registrar Evento                    | ms3-eventos       | Validar la creación de un evento con información válida.                | El evento existe y su estado corresponde al valor asignado (ABIERTO).          | Unitaria |
| CP-05 | Registrar Grupo de Evento           | ms4-grupos        | Validar la asociación de un grupo a un evento.                          | El grupo existe y su rol corresponde al valor asignado (PARTICIPANTE).         | Unitaria |
| CP-06 | Registrar Invitación                | ms4-grupos        | Validar la creación de una invitación asociada a un evento.             | La invitación existe y su estado corresponde al valor asignado (PENDIENTE).    | Unitaria |
| CP-07 | Registrar Votación                  | ms5-votaciones    | Validar la creación de una votación con información válida.             | La votación existe y su estado corresponde al valor asignado (ABIERTA).        | Unitaria |
| CP-08 | Registrar Opción de Votación        | ms5-votaciones    | Validar la creación de una opción de votación.                          | La opción existe y se encuentra activa.                                        | Unitaria |
| CP-09 | Registrar Temática                  | ms6-tematicas     | Validar la creación de una temática con información válida.             | La temática existe y su nombre no es nulo.                                     | Unitaria |
| CP-10 | Registrar Voto de Usuario           | ms6-tematicas     | Validar la asociación de un voto a una votación y una opción.           | El voto existe y su identificador de votación no es nulo.                      | Unitaria |
| CP-11 | Registrar Actividad                 | ms7-actividades   | Validar la creación de una actividad con información válida.            | La actividad existe y su tipo corresponde al valor asignado (JUEGO).           | Unitaria |
| CP-12 | Registrar Actividad de Evento       | ms7-actividades   | Validar la asociación de una actividad a un evento.                     | La actividad de evento existe y su estado corresponde al valor asignado (PENDIENTE). | Unitaria |
| CP-13 | Registrar Consumible                | ms8-consumibles   | Validar la creación de un consumible con información válida.            | El consumible existe y su categoría corresponde al valor asignado (BEBIDA).    | Unitaria |
| CP-14 | Registrar Asignación de Consumible  | ms9-asignacion    | Validar la asignación de un consumible a un usuario dentro de un evento.| La asignación existe y su estado corresponde al valor asignado (PENDIENTE).    | Unitaria |
| CP-15 | Registrar Ruleta de Consumible      | ms10-ruleta       | Validar el registro del resultado de una ruleta de consumibles.         | El resultado existe y su estado corresponde al valor asignado (SORTEADO).      | Unitaria |
