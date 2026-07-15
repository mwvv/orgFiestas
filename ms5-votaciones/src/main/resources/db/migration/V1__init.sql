CREATE TABLE votacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    evento_id INT NOT NULL,
    tema VARCHAR(100) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ABIERTA',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

CREATE TABLE opcion_votacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    votacion_id INT NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    total_votos INT DEFAULT 0,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL,
    FOREIGN KEY (votacion_id) REFERENCES votacion(id)
);

INSERT INTO votacion (evento_id, tema, estado, activo, fecha_registro)
VALUES (1, 'Tematica de la junta del Olimpo', 'ABIERTA', TRUE, CURDATE());

INSERT INTO votacion (evento_id, tema, estado, activo, fecha_registro)
VALUES (2, 'Tematica de la noche UNSC', 'ABIERTA', TRUE, CURDATE());

INSERT INTO opcion_votacion (votacion_id, descripcion, total_votos, activo, fecha_registro)
VALUES (1, 'Guerreros Miticos', 0, TRUE, CURDATE());

INSERT INTO opcion_votacion (votacion_id, descripcion, total_votos, activo, fecha_registro)
VALUES (1, 'Dioses del Olimpo', 0, TRUE, CURDATE());

INSERT INTO opcion_votacion (votacion_id, descripcion, total_votos, activo, fecha_registro)
VALUES (1, 'Terror Espacial', 0, TRUE, CURDATE());

INSERT INTO opcion_votacion (votacion_id, descripcion, total_votos, activo, fecha_registro)
VALUES (2, 'Ciencia Ficcion', 0, TRUE, CURDATE());

INSERT INTO opcion_votacion (votacion_id, descripcion, total_votos, activo, fecha_registro)
VALUES (2, 'Combate Futurista', 0, TRUE, CURDATE());
