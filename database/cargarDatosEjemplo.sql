-- Insertar 10 alumnos
INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado)
VALUES
    (12345678, 'Perez', 'Juan', '2000-01-15', 1),
    (23456789, 'Gomez', 'Maria', '2001-05-20', 1),
    (34567890, 'Lopez', 'Carlos', '2002-07-10', 1),
    (45678901, 'Rodriguez', 'Laura', '2003-03-25', 1),
    (56789012, 'Fernandez', 'Diego', '2000-09-05', 1),
    (67890123, 'Gonzalez', 'Ana', '2001-11-30', 1),
    (78901234, 'Torres', 'Pedro', '2002-08-12', 1),
    (89012345, 'Diaz', 'Sofia', '2003-04-18', 1),
    (90123456, 'Martinez', 'Lucas', '2000-02-03', 1),
    (12340123, 'Sanchez', 'Camila', '2001-10-28', 1);

-- Insertar 25 materias aleatorias
INSERT INTO materia (nombre, año, estado)
VALUES
    ('Matemáticas', 1, 1),
    ('Historia', 1, 1),
    ('Ciencias Naturales', 1, 1),
    ('Lengua y Literatura', 1, 1),
    ('Educación Física', 1, 1),
    ('Música', 1, 1),
    ('Arte', 1, 1),
    ('Geografía', 1, 1),
    ('Tecnología', 1, 1),
    ('Programación', 2, 1),
    ('Física', 2, 1),
    ('Química', 2, 1),
    ('Biología', 2, 1),
    ('Inglés', 2, 1),
    ('Historia del Arte', 2, 1),
    ('Filosofía', 2, 1),
    ('Economía', 2, 1),
    ('Psicología', 2, 1),
    ('Dibujo Técnico', 2, 1),
    ('Educación Cívica', 2, 1),
    ('Diseño Gráfico', 3, 1),
    ('Estadística', 3, 1),
    ('Sociología', 3, 1),
    ('Historia Universal', 3, 1),
    ('Derecho', 3, 1);

-- Inscribir a todos los alumnos en 3 materias aleatorias
INSERT INTO inscripcion (idAlumno, idMateria, nota)
SELECT
    a.idAlumno,
    m.idMateria,
    FLOOR(1 + RAND() * 10)  -- Generar nota aleatoria entre 1 y 10
FROM
    alumno a
JOIN
    (SELECT idMateria FROM materia WHERE estado = 1 ORDER BY RAND() LIMIT 3) AS m
-- Asegurarse de que un alumno no se inscriba en la misma materia más de una vez
LEFT JOIN
    inscripcion i ON a.idAlumno = i.idAlumno AND m.idMateria = i.idMateria
WHERE
    i.idInscripcion IS NULL;