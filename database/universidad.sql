-- Crear la base de dato
CREATE DATABASE universidad;
USE universidad;

-- Creacion de tablas
CREATE TABLE alumno(
    idAlumno int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dni int UNIQUE,
    apellido varchar(100),
    nombre varchar(100),
    fechaNacimiento date,
    estado tinyint(1)
);

CREATE TABLE materia(
    idMateria int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre varchar(100) UNIQUE,
    año int(11),
    estado tinyint(1)
);

CREATE TABLE inscripcion(
    idInscripcion int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nota double,
    idAlumno int, FOREIGN KEY (idAlumno) REFERENCES alumno(idAlumno),
    idMateria int, FOREIGN KEY (idMateria) REFERENCES materia(idMateria)
);