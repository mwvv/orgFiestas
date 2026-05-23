CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    foto_perfil VARCHAR(200),
    fecha_nacimiento DATE NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_registro DATE NOT NULL
);

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Juan Perez', 'juan@email.com', '123456', '1995-05-10', 'ACTIVO', TRUE, CURDATE());

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Maria Lopez', 'maria@email.com', '123456', '1998-03-22', 'ACTIVO', TRUE, CURDATE());
