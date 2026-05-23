CREATE TABLE actividad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    duracion_minutos INT,
    tematica_id INT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

CREATE TABLE actividad_evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    actividad_id INT NOT NULL,
    evento_id INT NOT NULL,
    hora_inicio TIME,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL,
    FOREIGN KEY (actividad_id) REFERENCES actividad(id)
);

INSERT INTO actividad (nombre, descripcion, duracion_minutos, tematica_id, tipo, activo, fecha_registro)
VALUES ('Karaoke ochentero', 'Canciones de los anos 80', 60, 1, 'KARAOKE', TRUE, CURDATE());

INSERT INTO actividad (nombre, descripcion, duracion_minutos, tematica_id, tipo, activo, fecha_registro)
VALUES ('Trivia de los 80', 'Preguntas sobre cultura pop de los 80', 30, 1, 'CONCURSO', TRUE, CURDATE());

INSERT INTO actividad_evento (actividad_id, evento_id, hora_inicio, estado, activo, fecha_registro)
VALUES (1, 1, '20:00:00', 'PENDIENTE', TRUE, CURDATE());
