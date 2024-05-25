DROP DATABASE IF EXISTS santaCruz;

CREATE DATABASE santaCruz;

USE santaCruz;

CREATE TABLE rol(
	idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombreRol VARCHAR(15)
);
insert rol values (null, 'administrador'),(null, 'profesor'),(null,'estudiante');

CREATE TABLE estado(
	idEstado INT AUTO_INCREMENT PRIMARY KEY,
    nombreEstado VARCHAR(15)
);
insert estado values (null, 'No activo'),(null,'Activo');

CREATE TABLE usuario(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombres VARCHAR(30),
    apePaterno VARCHAR(15),
    apeMaterno VARCHAR(15),
    celular CHAR(9),
    dni CHAR(8) unique,
    clave CHAR(8),
    idRol INT,
    idEstado INT,
    FOREIGN KEY (idRol) REFERENCES rol (idRol),
    FOREIGN KEY (idEstado) REFERENCES estado (idEstado)
);
insert usuario values
(null, 'Andy Camilo', 'Vargas', 'Vargas', '936545897','74743111', '15926315', 1, 2),
(null, 'Pedro Alvarado', 'Rojas', 'Verde', '967546567','87656765', '12345678', 2, 2),
(null, 'Dona', 'Ponte', 'Morales', '987876564','98767856', '45672343', 2, 2),
(null, 'Max', 'Orlando', 'Valenzuela', '925456432','76876565', '23435423', 2, 2),
(null, 'Yohana', 'Flores', 'Vega', '978654345','67546756', '76456123', 2, 2),
(null, 'Ana', 'González', 'Martínez', '936545898','75743112', '15926423', 2, 2),
(null, 'Juan', 'López', 'Pérez', '967546568','87656766', '12346121', 2, 2),
(null, 'María', 'Díaz', 'Gómez', '987876565','98767857', '45682341', 2, 2),
(null, 'Carlos', 'Martínez', 'García', '925456433','76876566', '23474565', 2, 2),
(null, 'Laura', 'Sánchez', 'López', '978654346','67546757', '76457154', 2, 2),
(null, 'José', 'Gutiérrez', 'Hernández', '936545899','75743113', '15926523', 2, 2),
(null, 'Marcela', 'Ramírez', 'Fernández', '967546569','87656767', '12347453', 2, 2),
(null, 'Pablo', 'Gómez', 'Sánchez', '987876566','98767858', '45692312', 2, 2),
(null, 'Luisa', 'García', 'Martínez', '925456434','76876567', '23483454', 2, 2),
(null, 'Javier', 'Hernández', 'Díaz', '978654347','67546758', '76458412', 2, 2),
(null, 'Martha', 'Vargas', 'Gómez', '936545800', '75743000', '15920054', 2, 2),
(null, 'Lucas', 'Valencia', 'Vargas', '967546500', '87656000', '12340212', 2, 2),
(null, 'Carmen', 'Pérez', 'Morales', '987876500', '98767000', '45605465', 2, 2),
(null, 'Elena', 'González', 'García', '925456400', '76876000', '23402354', 2, 2),
(null, 'Roberto', 'Martínez', 'Vega', '978654300', '67546000', '76400123', 2, 2),
(null, 'Yadira', 'Auccasi', 'Loa', '978654300', '72858157', '12345845', 3, 2);

CREATE TABLE nivel(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombreNivel VARCHAR(15)
);
insert nivel values (null, 'primaria'),(null, 'secundaria');

CREATE TABLE grado(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombreGrado VARCHAR(15)
);
insert grado values (null, 'primero'),(null, 'segundo'),(null, 'tercero'),(null, 'cuarto'),(null, 'quinto'),(null, 'sexto');

CREATE TABLE nivel_grado(
	idNivel INT,
    idGrado INT,
    FOREIGN KEY (idNivel) REFERENCES nivel (id),
    FOREIGN KEY (idGrado) REFERENCES grado (id),
    primary key (idNivel, idGrado)
);

CREATE TABLE matricula(
	idMatricula INT AUTO_INCREMENT PRIMARY KEY,
    idEstudiante INT,
    fecha DATE,
    idNivel INT,
    idGrado INT,
    FOREIGN KEY (idEstudiante) REFERENCES usuario (id),
    FOREIGN KEY (idNivel) REFERENCES nivel (id),
    FOREIGN KEY (idGrado) REFERENCES grado (id)
);

CREATE TABLE categoria(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(15)
);
insert categoria values
(null, 'matemáticas'),
(null, 'letras'),
(null, 'ciencias'),
(null, 'idiomas'),
(null, 'recreativa'),
(null, 'tecnología');

CREATE TABLE aula(
	codAula CHAR(4) PRIMARY KEY,
    cantActual INT,
    cantMax INT,
    estado INT
);
insert aula values
('A101',10, 30, 2),
('A202',6, 40, 2),
('B303',9, 20, 2),
('C401',11, 25, 2),
('A102',11,15, 2),
('B201',9, 10, 2),
('C102',24, 25, 2),
('B203',15, 30, 2),
('A304',35,  40, 2),
('A201',23, 35, 2),
('B102',16, 30, 2),
('A303',0, 30, 2),
('B404',0, 30, 2),
('C505',0, 30, 2),
('A503',0, 30, 2),
('B301',0, 30, 2),
('C302',0, 30, 2),
('A402',0, 30, 2),
('B409',0, 30, 2),
('A205',0, 30, 2),
('A121',0, 30, 2);

CREATE TABLE horario(
	id INT AUTO_INCREMENT PRIMARY KEY,
    dia VARCHAR(15),
    horaInicio TIME,
    horaFin TIME,
    codAula CHAR(4),
    FOREIGN KEY (codAula) REFERENCES aula (codAula)
);
insert horario values
(null, 'Lunes', '08:00:00', '10:00:00', 'A101'),
(null, 'Martes', '10:00:00', '12:00:00', 'A202'),
(null, 'Miércoles', '09:00:00', '11:00:00', 'B303'),
(null , 'Jueves', '08:00:00', '10:00:00', 'C401'),
(null , 'Viernes', '09:30:00', '11:30:00', 'A102'),
(null , 'Sábado', '10:00:00', '12:00:00', 'B201'),
(null, 'Lunes', '08:30:00', '10:30:00', 'C102'),
(null, 'Martes', '09:00:00', '11:00:00', 'B203'),
(null, 'Miércoles', '08:00:00', '10:00:00', 'A304'),
(null, 'Jueves', '09:30:00', '11:30:00', 'A201'),
(null, 'Viernes', '08:30:00', '10:30:00', 'B102'),
(null, 'Lunes', '13:00:00', '15:00:00', 'A303'),
(null, 'Martes', '14:00:00', '16:00:00', 'B404'),
(null, 'Miércoles', '11:00:00', '13:00:00', 'C505'),
(null, 'Jueves', '15:00:00', '17:00:00', 'A503'),
(null, 'Viernes', '12:30:00', '14:30:00', 'B301'),
(null, 'Sábado', '08:00:00', '10:00:00', 'C302'),
(null, 'Lunes', '09:00:00', '11:00:00', 'A402'),
(null, 'Martes', '16:00:00', '18:00:00', 'B409'),
(null, 'Miércoles', '10:30:00', '12:30:00', 'A205'),
(null, 'Jueves', '14:00:00', '16:00:00', 'A121');

CREATE TABLE curso(
	idCurso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    idProfesor INT,
    idHorario INT,
    idCategoria INT,
    idNivel INT,
    precio float,
    FOREIGN KEY (idProfesor) REFERENCES usuario (id),
    FOREIGN KEY (idHorario) REFERENCES horario (id),
    FOREIGN KEY (idCategoria) REFERENCES categoria (id),
    FOREIGN KEY (idNivel) REFERENCES nivel (id)
);
insert curso values
(null,'Comunicación', 2, 19, 2, 1, 50.00),
(null,'Matemáticas', 3, 17, 1, 1, 60.00),
(null,'Ciencias Naturales', 4, 15, 3, 1, 55.00),
(null,'Ciencias Sociales', 5, 13, 3, 1, 45.00),
(null,'Educación Física', 6, 11, 5, 1, 50.00),
(null,'Educación Religiosa', 7, 5, 4, 1, 55.00),
(null,'Arte y Cultura', 8, 18, 5, 1, 60.00),
(null,'Inglés', 9, 16, 4, 1, 45.00),
(null,'Computación', 10, 14, 6, 1, 50.00),
(null,'Personal Social', 11, 12, 3, 1, 50.00),
(null,'Cívica', 2, 9, 2, 1, 55.00),
(null,'Ética y Valores', 3, 7, 2, 1, 60.00),
(null,'Física', 4, 5, 3, 2, 45.00),
(null,'Química', 5, 3, 3, 2, 50.00),
(null,'Biología', 6, 1, 3, 2, 55.00),
(null,'Historia', 7, 10, 3, 2, 60.00),
(null,'Geografía', 8, 8, 3, 2, 45.00),
(null,'Literatura', 9, 6, 2, 2, 50.00),
(null,'Filosofía', 10, 4, 2, 2, 55.00),
(null,'Psicología', 11, 2, 3, 2, 60.00);

CREATE TABLE detalleMatricula(
	idMatricula INT,
    idCurso INT,
    FOREIGN KEY (idCurso) REFERENCES curso (idCurso),
    PRIMARY KEY (idMatricula, idCurso)
);

CREATE TABLE nota(
	idNota INT AUTO_INCREMENT PRIMARY KEY,
    idMatricula INT,
    idCurso INT,
    nota FLOAT,
    fechaNota DATE,
    FOREIGN KEY (idMatricula) REFERENCES matricula (idMatricula),
    FOREIGN KEY (idCurso) REFERENCES curso (idCurso)
);

DELIMITER //
CREATE PROCEDURE usp_validarAcceso(dniUsu CHAR(8), claveUsu CHAR(8))
BEGIN
	SELECT * FROM usuario WHERE dni = dniUsu AND clave = claveUsu AND idEstado = 2;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE usp_validarRegistro(dniUsu CHAR(8), idRolUsu INT)
BEGIN
	SELECT * FROM usuario WHERE dni = dniUsu AND idRol = idRolUsu AND idEstado = 1;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE usp_salonesAsignados()
BEGIN
	SELECT h.*
	FROM horario h
	JOIN aula a ON h.codAula = a.codAula
	WHERE a.estado = 1;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE cambiarEstadoAula(IN idCursoParam INT)
BEGIN
    DECLARE codAulaCurso CHAR(4);
    
    -- Obtener el código del aula asociado con el curso
    SELECT h.codAula INTO codAulaCurso
    FROM curso c
    JOIN horario h ON c.idHorario = h.id
    WHERE c.idCurso = idCursoParam;
    
    -- Actualizar el estado del aula a 2
    UPDATE aula SET estado = 2 WHERE codAula = codAulaCurso;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerDetalleMatriculaInfo()
BEGIN
    SELECT dm.idMatricula,
           dm.idCurso,
           c.nombre,
           u.nombres,
           h.dia,
           CONCAT(h.horaInicio, '-', h.horaFin) AS horaInicioFin,
           a.codAula AS aula,
           c.precio
    FROM detalleMatricula dm
    INNER JOIN curso c ON dm.idCurso = c.idCurso
    INNER JOIN horario h ON c.idHorario = h.id
    INNER JOIN usuario u ON c.idprofesor = u.id
    INNER JOIN aula a ON h.codAula = a.codAula;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerCursoInfo()
BEGIN
	SELECT 
		c.idCurso,
		c.nombre,
		CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) AS profesor,
		CONCAT(h.dia, ' ', h.horaInicio, ' - ', h.horaFin) AS horario,
		h.codAula AS codigo_aula,
		c.precio
	FROM 
		curso c
	INNER JOIN 
		usuario u ON c.idProfesor = u.id
	INNER JOIN 
		horario h ON c.idHorario = h.id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerCursoInfoPorNivel(IN idNivelParam INT)
BEGIN
	SELECT 
		c.idCurso,
		c.nombre,
		CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) AS profesor,
		CONCAT(h.dia, ' ', h.horaInicio, ' - ', h.horaFin) AS horario,
		h.codAula AS codigo_aula,
		c.precio
	FROM 
		curso c
	INNER JOIN 
		usuario u ON c.idProfesor = u.id
	INNER JOIN 
		horario h ON c.idHorario = h.id
	INNER JOIN
		nivel n ON c.idNivel = n.id
	WHERE
		n.id = idNivelParam;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerCursoInfoPorNombre(IN nombreCursoParam VARCHAR(30))
BEGIN
    SELECT 
        c.idCurso,
        c.nombre,
        CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) AS profesor,
        CONCAT(h.dia, ' ', h.horaInicio, ' - ', h.horaFin) AS horario,
        h.codAula AS codigo_aula,
        c.precio
    FROM 
        curso c
    INNER JOIN 
        usuario u ON c.idProfesor = u.id
    INNER JOIN 
        horario h ON c.idHorario = h.id
    WHERE 
        c.nombre LIKE nombreCursoParam;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerCursoInfoPorProfesor(IN nombreCompletoProfesorParam VARCHAR(100))
BEGIN
    SELECT 
        c.idCurso,
        c.nombre,
        CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) AS profesor,
        CONCAT(h.dia, ' ', h.horaInicio, ' - ', h.horaFin) AS horario,
        h.codAula AS codigo_aula,
        c.precio
    FROM 
        curso c
    INNER JOIN 
        usuario u ON c.idProfesor = u.id
    INNER JOIN 
        horario h ON c.idHorario = h.id
    WHERE 
        CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) LIKE nombreCompletoProfesorParam;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE obtenerCursoInfoPorCodigoAula(IN codAulaParam CHAR(4))
BEGIN
    SELECT 
        c.idCurso,
        c.nombre,
        CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) AS profesor,
        CONCAT(h.dia, ' ', h.horaInicio, ' - ', h.horaFin) AS horario,
        h.codAula AS codigo_aula,
        c.precio
    FROM 
        curso c
    INNER JOIN 
        usuario u ON c.idProfesor = u.id
    INNER JOIN 
        horario h ON c.idHorario = h.id
    WHERE 
        h.codAula LIKE codAulaParam;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE cambiarEstadoAulaPorCurso(IN idCursoParam INT)
BEGIN
    DECLARE codAulaCurso CHAR(4);
    
    -- Obtener el código del aula asociado con el curso
    SELECT h.codAula INTO codAulaCurso
    FROM curso c
    JOIN horario h ON c.idHorario = h.id
    WHERE c.idCurso = idCursoParam;
    
    -- Actualizar el estado del aula a 1 (activo)
    UPDATE aula SET estado = 1 WHERE codAula = codAulaCurso;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE ActualizarCapacidadAulasPorMatricula(IN idMatriculaParam INT)
BEGIN
    -- Actualizar la capacidad actual de las aulas relacionadas a la matrícula
    UPDATE aula
    SET cantActual = cantActual + 1
    WHERE codAula IN (
        SELECT h.codAula
        FROM detalleMatricula dm
        INNER JOIN curso c ON dm.idCurso = c.idCurso
        INNER JOIN horario h ON c.idHorario = h.id
        WHERE dm.idMatricula = idMatriculaParam
    );
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE buscarAlumnoMatriculadoPorAnio(IN idEstudianteParam INT)
BEGIN
    DECLARE anioActual INT;
    
    -- Obtener el año actual del sistema
    SET anioActual = YEAR(CURDATE());
    
    -- Buscar la información del estudiante y su matrícula para el año actual
    SELECT u.*
    FROM usuario u
    INNER JOIN matricula m ON u.id = m.idEstudiante
    WHERE u.id = idEstudianteParam
    AND YEAR(m.fecha) = anioActual;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE ReporteMatriculasPorAnioYNivel(
    IN anioBuscado YEAR,
    IN nivelBuscado INT
)
BEGIN
    SELECT 
        m.idMatricula,
        CONCAT(u.nombres, ' ', u.apePaterno, ' ', u.apeMaterno) AS estudiante,
        u.dni,
        m.fecha,
        n.nombreNivel AS nivel,
        g.nombreGrado AS grado
    FROM
        matricula m
    INNER JOIN
        usuario u ON m.idEstudiante = u.id
    INNER JOIN
        nivel n ON m.idNivel = n.id
    INNER JOIN
        grado g ON m.idGrado = g.id
    WHERE
        YEAR(m.fecha) = anioBuscado
        AND n.id = nivelBuscado;
END //
DELIMITER ;
