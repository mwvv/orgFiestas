CREATE TABLE evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    fecha_evento DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    lugar VARCHAR(200) NOT NULL,
    anfitrion_id INT NOT NULL,
    maximo_invitados INT,
    estado VARCHAR(20) NOT NULL DEFAULT 'ABIERTO',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO evento (nombre, descripcion, fecha_evento, hora_inicio, lugar, anfitrion_id, maximo_invitados, estado, activo, fecha_registro)
VALUES ('Junta en el Olimpo', 'Reunion epica organizada por Kratos', '2026-07-15', '19:00:00', 'Monte Olimpo 123', 1, 10, 'ABIERTO', TRUE, CURDATE());

INSERT INTO evento (nombre, descripcion, fecha_evento, hora_inicio, lugar, anfitrion_id, maximo_invitados, estado, activo, fecha_registro)
VALUES ('Noche en la UNSC', 'Master Chief organiza la junta', '2026-08-20', '20:00:00', 'Base UNSC Reach', 2, 8, 'ABIERTO', TRUE, CURDATE());

INSERT INTO evento (nombre, descripcion, fecha_evento, hora_inicio, lugar, anfitrion_id, maximo_invitados, estado, activo, fecha_registro)
VALUES ('Survival Night', 'Steve arma una junta survival', '2026-09-10', '18:00:00', 'Casa de Steve, Overworld', 3, 6, 'ABIERTO', TRUE, CURDATE());