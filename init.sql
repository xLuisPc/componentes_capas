-- ══════════════════════════════════════════
--  Base de datos: SistemaClientes
-- ══════════════════════════════════════════
CREATE DATABASE IF NOT EXISTS db_clientes;

GRANT ALL PRIVILEGES ON db_clientes.* TO 'ccc_user'@'%';

USE db_clientes;

CREATE TABLE IF NOT EXISTS personas (
    identificacion DOUBLE       PRIMARY KEY,
    nombres        VARCHAR(100) NOT NULL,
    apellidos      VARCHAR(100) NOT NULL,
    edad           INT          NOT NULL
);

INSERT INTO personas (identificacion, nombres, apellidos, edad) VALUES
(1001.0, 'Carlos',  'Ramirez',   28),
(1002.0, 'Maria',   'Lopez',     34),
(1003.0, 'Jorge',   'Martinez',  45),
(1004.0, 'Lucia',   'Hernandez', 22),
(1005.0, 'Andres',  'Torres',    31);


-- ══════════════════════════════════════════
--  Base de datos: GestionInventario
-- ══════════════════════════════════════════
CREATE DATABASE IF NOT EXISTS db_inventario;

GRANT ALL PRIVILEGES ON db_inventario.* TO 'ccc_user'@'%';

USE db_inventario;

CREATE TABLE IF NOT EXISTS productos (
    id        INT          AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    precio    DOUBLE       NOT NULL,
    cantidad  INT          NOT NULL
);

INSERT INTO productos (nombre, precio, cantidad) VALUES
('Laptop HP',         1200.00, 10),
('Mouse Inalambrico',   25.50, 50),
('Teclado Mecanico',    85.00, 30),
('Monitor 24 pulgadas', 350.00, 15),
('Auriculares USB',     45.99, 25);

FLUSH PRIVILEGES;
