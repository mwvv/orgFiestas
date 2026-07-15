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
VALUES ('Hidromiel de Valhalla', 'Bebida vikinga especial', 'BEBIDA', 3500, TRUE, CURDATE());

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Pizza del Olimpo', 'Pizza familiar con ingredientes epicos', 'COMIDA', 9500, TRUE, CURDATE());

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Snack de Supervivencia', 'Mix de frutos secos estilo survival', 'SNACK', 2500, TRUE, CURDATE());

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Pocion Energetica', 'Bebida energetica inspirada en pociones de videojuegos', 'BEBIDA', 1800, TRUE, CURDATE());

INSERT INTO consumible (nombre, descripcion, categoria, precio, activo, fecha_registro)
VALUES ('Nachos UNSC', 'Nachos con salsa especial de la base UNSC', 'SNACK', 3000, TRUE, CURDATE());
