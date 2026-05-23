CREATE TABLE tematica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    imagen VARCHAR(200),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

CREATE TABLE voto_usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    votacion_id INT NOT NULL,
    opcion_id INT NOT NULL,
    usuario_id INT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO tematica (nombre, descripcion, activo, fecha_registro)
VALUES ('Anos 80', 'Musica y moda de los anos 80', TRUE, CURDATE());

INSERT INTO tematica (nombre, descripcion, activo, fecha_registro)
VALUES ('Playa', 'Ambiente tropical y de verano', TRUE, CURDATE());

INSERT INTO tematica (nombre, descripcion, activo, fecha_registro)
VALUES ('Terror', 'Ambientacion de miedo y suspenso', TRUE, CURDATE());

INSERT INTO voto_usuario (votacion_id, opcion_id, usuario_id, activo, fecha_registro)
VALUES (1, 1, 1, TRUE, CURDATE());
