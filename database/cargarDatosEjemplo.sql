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

-- Inscribir a todos los alumnos en 5 materias aleatorias
DELIMITER //
CREATE PROCEDURE GenerarInscripcionesAleatorias()
BEGIN
  DECLARE alumno_id INT;
  DECLARE materia_id INT;
  DECLARE nota DECIMAL(4, 2);
  
  -- Obtener el número total de alumnos y materias activas
  SELECT MIN(idAlumno) INTO alumno_id FROM alumno;
  SELECT COUNT(*) INTO materia_id FROM materia WHERE estado = 1;
  
  -- Verificar si hay alumnos y materias disponibles
  IF alumno_id IS NOT NULL AND materia_id > 0 THEN
    WHILE alumno_id IS NOT NULL DO
      -- Generar una materia aleatoria no inscrita para el alumno
      SET materia_id = (
        SELECT idMateria
        FROM materia
        WHERE estado = 1 AND idMateria NOT IN (
          SELECT idMateria
          FROM inscripcion
          WHERE idAlumno = alumno_id
        )
        ORDER BY RAND()
        LIMIT 1
      );
      
      -- Si se encontró una materia, generar una inscripción aleatoria
      IF materia_id IS NOT NULL THEN
        SET nota = ROUND(RAND() * 9 + 1, 2);
        INSERT INTO inscripcion (idAlumno, idMateria, nota)
        VALUES (alumno_id, materia_id, nota);
      END IF;
      
      -- Obtener el siguiente alumno
      SET alumno_id = (
        SELECT MIN(idAlumno)
        FROM alumno
        WHERE idAlumno > alumno_id
      );
    END WHILE;
    
    SELECT 'Inscripciones generadas exitosamente.' AS Resultado;
  ELSE
    SELECT 'No hay alumnos o materias disponibles para inscripción.' AS Resultado;
  END IF;
END //
DELIMITER ;

CALL GenerarInscripcionesAleatorias();
CALL GenerarInscripcionesAleatorias();
CALL GenerarInscripcionesAleatorias();
CALL GenerarInscripcionesAleatorias();
CALL GenerarInscripcionesAleatorias();