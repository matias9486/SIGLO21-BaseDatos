CREATE DATABASE IF NOT EXISTS ejercicio3;
USE ejercicio3;

CREATE TABLE IF NOT EXISTS alumnos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  nota DOUBLE NOT NULL
);

INSERT INTO alumnos(nombre, apellido,nota) 
values('Matias', 'Alancay', 10),
	('Maria', 'Garcia', 7.0 ),
	('Pedro', 'Lopez', 8.5);