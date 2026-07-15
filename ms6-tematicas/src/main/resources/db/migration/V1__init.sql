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
VALUES ('Guerreros Miticos', 'Tematica basada en dioses y guerreros de la mitologia', TRUE, CURDATE());

INSERT INTO tematica (nombre, descripcion, activo, fecha_registro)
VALUES ('Ciencia Ficcion', 'Tematica espacial y futurista', TRUE, CURDATE());

INSERT INTO tematica (nombre, descripcion, activo, fecha_registro)
VALUES ('Survival', 'Tematica de supervivencia y aventura', TRUE, CURDATE());

INSERT INTO tematica (nombre, descripcion, activo, fecha_registro)
VALUES ('Terror', 'Tematica de miedo y suspenso', TRUE, CURDATE());

INSERT INTO voto_usuario (votacion_id, opcion_id, usuario_id, activo, fecha_registro)
VALUES (1, 1, 2, TRUE, CURDATE());

INSERT INTO voto_usuario (votacion_id, opcion_id, usuario_id, activo, fecha_registro)
VALUES (1, 2, 3, TRUE, CURDATE());

INSERT INTO voto_usuario (votacion_id, opcion_id, usuario_id, activo, fecha_registro)
VALUES (2, 4, 5, TRUE, CURDATE());
