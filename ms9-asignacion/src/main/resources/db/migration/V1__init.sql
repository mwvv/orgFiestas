CREATE TABLE asignacion_consumible (
    id INT AUTO_INCREMENT PRIMARY KEY,
    evento_id INT NOT NULL,
    usuario_id INT NOT NULL,
    consumible_id INT NOT NULL,
    cantidad INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO asignacion_consumible (evento_id, usuario_id, consumible_id, cantidad, estado, activo, fecha_registro)
VALUES (1, 1, 1, 3, 'CONFIRMADA', TRUE, CURDATE());

INSERT INTO asignacion_consumible (evento_id, usuario_id, consumible_id, cantidad, estado, activo, fecha_registro)
VALUES (1, 2, 2, 2, 'CONFIRMADA', TRUE, CURDATE());

INSERT INTO asignacion_consumible (evento_id, usuario_id, consumible_id, cantidad, estado, activo, fecha_registro)
VALUES (1, 3, 3, 4, 'PENDIENTE', TRUE, CURDATE());

INSERT INTO asignacion_consumible (evento_id, usuario_id, consumible_id, cantidad, estado, activo, fecha_registro)
VALUES (2, 5, 1, 2, 'PENDIENTE', TRUE, CURDATE());