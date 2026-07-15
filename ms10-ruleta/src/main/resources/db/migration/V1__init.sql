CREATE TABLE ruleta_consumible (
    id INT AUTO_INCREMENT PRIMARY KEY,
    evento_id INT NOT NULL,
    consumible_id INT NOT NULL,
    usuario_asignado_id INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'SORTEADO',
    fecha_sorteo DATE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO ruleta_consumible (evento_id, consumible_id, usuario_asignado_id, estado, fecha_sorteo, activo, fecha_registro)
VALUES (1, 1, 1, 'CONFIRMADO', CURDATE(), TRUE, CURDATE());

INSERT INTO ruleta_consumible (evento_id, consumible_id, usuario_asignado_id, estado, fecha_sorteo, activo, fecha_registro)
VALUES (1, 2, 2, 'SORTEADO', CURDATE(), TRUE, CURDATE());

INSERT INTO ruleta_consumible (evento_id, consumible_id, usuario_asignado_id, estado, fecha_sorteo, activo, fecha_registro)
VALUES (1, 3, 3, 'SORTEADO', CURDATE(), TRUE, CURDATE());

INSERT INTO ruleta_consumible (evento_id, consumible_id, usuario_asignado_id, estado, fecha_sorteo, activo, fecha_registro)
VALUES (2, 1, 5, 'CONFIRMADO', CURDATE(), TRUE, CURDATE());
