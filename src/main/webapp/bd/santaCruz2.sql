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
insert usuario values (null, 'Andy Camilo', 'Vargas', 'Vargas', '936545897','74743111', '159263', 1, 2);

CREATE TABLE nivel(
	idNivel INT AUTO_INCREMENT PRIMARY KEY,
    nombreNivel VARCHAR(15)
);
insert nivel values (null, 'primaria'),(null, 'secundaria');

CREATE TABLE grado(
	idGrado INT AUTO_INCREMENT PRIMARY KEY,
    nombreGrado VARCHAR(15)
);
insert nivel values (null, 'primero'),(null, 'segundo'),(null, 'tercero'),(null, 'cuarto'),(null, 'quinto'),(null, 'sexto');

CREATE TABLE nivel_grado(
	idNivel INT,
    idGrado INT,
    FOREIGN KEY (idNivel) REFERENCES nivel (idNivel),
    FOREIGN KEY (idGrado) REFERENCES grado (idGrado),
    primary key (idNivel, idGrado)
);

CREATE TABLE matricula(
	idMatricula INT AUTO_INCREMENT PRIMARY KEY,
    dniEstudiante CHAR(8),
    fecha DATE,
    idNivel INT,
    idGrado INT,
    FOREIGN KEY (dniEstudiante) REFERENCES usuario (dni),
    FOREIGN KEY (idNivel) REFERENCES nivel (idNivel),
    FOREIGN KEY (idGrado) REFERENCES grado (idGrado)
);

CREATE TABLE categoria(
	idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(15)
);

CREATE TABLE curso(
	idCurso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    idCategoria INT,
    FOREIGN KEY (idCategoria) REFERENCES categoria (idCategoria)
);

CREATE TABLE horario(
	idHorario INT PRIMARY KEY,
    dia VARCHAR(15),
    horaInicio TIME,
    horaFin TIME,
    aula VARCHAR(10),
    idCurso INT,
    FOREIGN KEY (idCurso) REFERENCES curso(idCurso)
);

CREATE TABLE curso_profesor(
	idCurso INT,
    dniProfesor CHAR(8),
    FOREIGN KEY (idCurso) REFERENCES curso (idCurso),
    FOREIGN KEY (dniProfesor) REFERENCES usuario (dni),
    PRIMARY KEY (idCurso, dniProfesor)
);

CREATE TABLE detalleMatricula(
	idMatricula INT,
    idCurso INT,
    idHorario INT,
    idProfesor INT,
    precio FLOAT,
    FOREIGN KEY (idMatricula) REFERENCES matricula (idMatricula),
    FOREIGN KEY (idCurso) REFERENCES curso (idCurso),
    FOREIGN KEY (idHorario) REFERENCES horario (idHorario),
    FOREIGN KEY (idProfesor) REFERENCES usuario (id),
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