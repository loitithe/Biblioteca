DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE socios (
    dni CHAR(9) PRIMARY KEY UNIQUE,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    index(dni)
)DEFAULT CHARSET=UTF8 ENGINE InnoDB;


CREATE TABLE libros (
    cod_libro CHAR(9) PRIMARY KEY UNIQUE,
    title VARCHAR(40) NOT NULL,
    author VARCHAR(100) NOT NULL,
    disponible BOOLEAN default 1,
    index(cod_libro)
)DEFAULT CHARSET=UTF8 ENGINE InnoDB;


CREATE TABLE alquiler (
    cod_alquiler int PRIMARY KEY auto_increment,
    cod_libro CHAR(9) NOT NULL,
    dni_socio CHAR(9) NOT NULL,
    fecha_inicio timestamp DEFAULT current_timestamp,
    fecha_devolucion DATETIME default null,
    index(dni_socio),
    index(cod_libro),
    foreign key(dni_socio)references socios(dni) on delete restrict on update cascade,
    foreign key(cod_libro)references libros(cod_libro) on delete restrict on update cascade
    
)DEFAULT CHARSET=UTF8 ENGINE InnoDB;

INSERT INTO socios VALUES ('11111111A','alex','garcia');
INSERT INTO socios VALUES ('22222222B','pablo','perez');
INSERT INTO socios VALUES ('33333333C','sergio','carrera');
INSERT INTO socios VALUES ('44444444D','alfredo','wisceslao');

INSERT INTO libros (cod_libro,title,author) VALUES ('abc111111','El Resplandor','Stanley Kubrick');
INSERT INTO libros (cod_libro,title,author) VALUES ('abc111112','El Resplandor','Stanley Kubrick');
INSERT INTO libros (cod_libro,title,author) VALUES ('cde222222','Memento','Christopher Nolan');
INSERT INTO libros (cod_libro,title,author) VALUES ('cde222221','Memento','Christopher Nolan');
INSERT INTO libros (cod_libro,title,author) VALUES ('fgh333333','Godzilla','Gareth Edwards');
INSERT INTO libros (cod_libro,title,author) VALUES ('fgh333331','Godzilla','Gareth Edwards');
INSERT INTO libros (cod_libro,title,author) VALUES ('ijf444444','La Biblia','Yisus');
INSERT INTO libros (cod_libro,title,author) VALUES ('ijf444441','La Biblia','Yisus');