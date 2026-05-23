CREATE TABLE consumible (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    categoria VARCHAR(20) NOT NULL,
    precio DOUBLE,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Coca Cola 1.5L', 'Bebida gaseosa', 'BEBIDA', 1500, TRUE, CURDATE());

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Papas fritas', 'Snack salado', 'SNACK', 800, TRUE, CURDATE());

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Pizza', 'Pizza familiar', 'COMIDA', 8000, TRUE, CURDATE());
