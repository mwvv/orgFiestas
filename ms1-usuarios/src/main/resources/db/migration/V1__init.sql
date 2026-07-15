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
VALUES ('Kratos', 'kratos@godofwar.com', '123456', '1980-03-15', 'ACTIVO', TRUE, CURDATE());

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Master Chief', 'masterchief@unsc.com', '123456', '1984-03-07', 'ACTIVO', TRUE, CURDATE());

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Steve', 'steve@minecraft.com', '123456', '1992-11-18', 'ACTIVO', TRUE, CURDATE());

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Lara Croft', 'lara@tombraider.com', '123456', '1992-02-14', 'ACTIVO', TRUE, CURDATE());

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Arthur Morgan', 'arthur@rdr2.com', '123456', '1975-06-14', 'ACTIVO', TRUE, CURDATE());

INSERT INTO usuario (nombre, email, contrasena, fecha_nacimiento, estado, activo, fecha_registro)
VALUES ('Geralt of Rivia', 'geralt@witcher.com', '123456', '1970-07-15', 'ACTIVO', TRUE, CURDATE());