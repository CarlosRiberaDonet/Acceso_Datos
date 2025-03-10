CREATE DATABASE incidencias;
DROP DATABASE incidencias;
USE incidencias;

CREATE TABLE empleado(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre_usuario VARCHAR(30) UNIQUE NOT NULL ,
contrasena VARCHAR(30) NOT NULL,
nombre_completo VARCHAR(50) NOT NULL,
telefono VARCHAR(9)
);

CREATE TABLE incidencia(
id INT AUTO_INCREMENT PRIMARY KEY,
fecha DATETIME NOT NULL,
id_empleado_origen INT NOT NULL,
id_empleado_destino INT NOT NULL,
detalle VARCHAR(255),
tipo CHAR(1) CHECK ( tipo IN ('U', 'N')) NOT NULL,
CONSTRAINT fk_empleado_origen FOREIGN KEY (id_empleado_origen) REFERENCES empleado (id) ON DELETE CASCADE,
CONSTRAINT fk_empleado_destino FOREIGN KEY(id_empleado_destino) REFERENCES empleado(id) ON DELETE CASCADE
);



