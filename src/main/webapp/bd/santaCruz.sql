DROP DATABASE IF EXISTS santaCruz;

CREATE DATABASE santaCruz;

USE santaCruz;

CREATE TABLE administrador(
	dniAdmin CHAR(8) PRIMARY KEY,
    nombres VARCHAR(30),
    apePaterno VARCHAR(15),
    apeMaterno VARCHAR(15),
	celular CHAR(9)
);

insert administrador values ('74743111', 'Andy Camilo', 'Vargas', 'Vargas', '936545897');

CREATE TABLE profesor(
	dniProfesor CHAR(8) PRIMARY KEY,
    nombres VARCHAR(30),
    apePaterno VARCHAR(15),
    apeMaterno VARCHAR(15),
    celular CHAR(9)
);
select * from profesor;

CREATE TABLE estudiante(
	dniEstudiante CHAR(8) PRIMARY KEY,
    nombres VARCHAR(30),
    apePaterno VARCHAR(15),
    apeMaterno VARCHAR(15),
    celular CHAR(9)
);

CREATE TABLE rol(
	idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombreRol VARCHAR(15)
);

insert rol values (null, 'administrador'),(null, 'profesor'),(null,'estudiante');
select * from rol;

CREATE TABLE usuario(
	dni CHAR(8) PRIMARY KEY,
    clave CHAR(8),
    idRol INT, 
    FOREIGN KEY (idRol) REFERENCES rol (idRol)
);
insert usuario values ('74743111', '159263', 1);
select * from usuario;

CREATE TABLE nivel(
	idNivel INT AUTO_INCREMENT PRIMARY KEY,
    nombreNivel VARCHAR(15)
);

CREATE TABLE grado(
	idGrado INT AUTO_INCREMENT PRIMARY KEY,
    nombreGrado VARCHAR(15)
);

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
    FOREIGN KEY (dniEstudiante) REFERENCES estudiante (dniEstudiante),
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

CREATE TABLE curso_profesor(
	idCurso INT,
    dniProfesor CHAR(8),
    FOREIGN KEY (idCurso) REFERENCES curso (idCurso),
    FOREIGN KEY (dniProfesor) REFERENCES profesor (dniProfesor),
    PRIMARY KEY (idCurso, dniProfesor)
);

CREATE TABLE detalleMatricula(
	idMatricula INT,
    idCurso INT,
    dniProfesor CHAR(8),
    precio FLOAT,
    FOREIGN KEY (idMatricula) REFERENCES matricula (idMatricula),
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
	SELECT * FROM usuario WHERE dni = dniUsu AND clave = claveUsu;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE usp_validarRegistro(dniUsu CHAR(8), idRolUsu INT)
BEGIN
	DECLARE cargoUsuario VARCHAR(50);

    SELECT nombreRol INTO cargoUsuario FROM rol WHERE idRol = idRolUsu;
    
    CASE cargoUsuario
        WHEN 'administrador' THEN
            SELECT * FROM administrador WHERE dniAdmin = dniUsu;
        WHEN 'profesor' THEN
            SELECT * FROM profesor WHERE dniProfesor = dniUsu;
        WHEN 'estudiante' THEN
            SELECT * FROM estudiante WHERE dniEstudiante = dniUsu;
        ELSE
            SELECT 'Cargo no válido';
    END CASE;
END//
DELIMITER ;

CALL usp_validarRegistro('74743111', 1);