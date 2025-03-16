CREATE DATABASE empleado;

USE empleado;

CREATE TABLE empleados(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre_usuario VARCHAR(30) UNIQUE NOT NULL ,
contrasena VARCHAR(30) NOT NULL,
nombre_completo VARCHAR(50) NOT NULL,
telefono VARCHAR(9)
);
