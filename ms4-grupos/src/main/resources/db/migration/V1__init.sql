CREATE TABLE invitacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    evento_id INT NOT NULL,
    usuario_invitado_id INT NOT NULL,
    usuario_anfitrion_id INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    mensaje VARCHAR(200),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

CREATE TABLE grupo_evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    evento_id INT NOT NULL,
    usuario_id INT NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'PARTICIPANTE',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO invitacion (evento_id, usuario_invitado_id, usuario_anfitrion_id, estado, activo, fecha_registro)
VALUES (1, 2, 1, 'ACEPTADA', TRUE, CURDATE());

INSERT INTO grupo_evento (evento_id, usuario_id, rol, activo, fecha_registro)
VALUES (1, 1, 'ANFITRION', TRUE, CURDATE());

INSERT INTO grupo_evento (evento_id, usuario_id, rol, activo, fecha_registro)
VALUES (1, 2, 'PARTICIPANTE', TRUE, CURDATE());
