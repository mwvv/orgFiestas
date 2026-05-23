CREATE TABLE solicitud_amistad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_emisor_id INT NOT NULL,
    usuario_receptor_id INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    mensaje VARCHAR(200),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

CREATE TABLE amistad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id1 INT NOT NULL,
    usuario_id2 INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    fecha_amistad DATE,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO solicitud_amistad (usuario_emisor_id, usuario_receptor_id, estado, activo, fecha_registro)
VALUES (1, 2, 'PENDIENTE', TRUE, CURDATE());

INSERT INTO amistad (usuario_id1, usuario_id2, estado, activo, fecha_registro)
VALUES (1, 2, 'ACEPTADA', TRUE, CURDATE());
