﻿/*
Created: 27/09/2021
Modified: 03/10/2021
Model: prefieromizona
Database: MySQL 5.7
*/


-- Create tables section -------------------------------------------------

-- Table COMERCIOS

CREATE TABLE `COMERCIOS`
(
  `ID_COMERCIO` Int NOT NULL AUTO_INCREMENT,
  `COD_COMERCIO` Varchar(20),
  `RAZON_SOCIAL` Varchar(20) NOT NULL,
  `CIF` Varchar(8) NOT NULL,
  `NOMBRE_RESPONSABLE` Varchar(100),
  `APELLIDO1_RESPONSABLE` Varchar(200) NOT NULL,
  `APELLIDO2_RESPONSABLE` Varchar(20),
  `DIRECCION` Varchar(200) NOT NULL,
  `MUNICIPIO` Varchar(200) NOT NULL,
  `COD_POSTAL` Varchar(5) NOT NULL,
  PRIMARY KEY (`ID_COMERCIO`)
)
;

-- Table CLIENTES

CREATE TABLE `CLIENTES`
(
  `ID_CLIENTE` Int NOT NULL AUTO_INCREMENT,
  `COD_CLIENTE` Varchar(20) NOT NULL,
  `NOMBRE` Varchar(100),
  `APELLIDO1` Varchar(200),
  `APELLIDO2` Varchar(200),
  `DNI` Varchar(9),
  `TELEFONO` Varchar(20),
  PRIMARY KEY (`ID_CLIENTE`)
)
;

-- Table COMPRAS

CREATE TABLE `COMPRAS`
(
  `ID_COMPRA` Int NOT NULL AUTO_INCREMENT,
  `FECHA_COMPRA` Date NOT NULL,
  `PUNTOS` Int NOT NULL,
  `ID_COMERCIO` Int,
  `ID_CLIENTE` Int,
  PRIMARY KEY (`ID_COMPRA`)
)
;

-- Table CANJES

CREATE TABLE `CANJES`
(
  `ID_CANJE` Int NOT NULL AUTO_INCREMENT,
  `FECHA_CANJE` Date,
  `PUNTOS` Int NOT NULL,
  `ID_COMERCIO` Int,
  `ID_CLIENTE` Int,
  PRIMARY KEY (`ID_CANJE`)
)
;

-- Table PUNTOS

CREATE TABLE `PUNTOS`
(
  `ID_PUNTO` Int NOT NULL AUTO_INCREMENT,
  `TOTAL` Int NOT NULL,
  `ID_COMERCIO` Int,
  `ID_CLIENTE` Int,
  PRIMARY KEY (`ID_PUNTO`)
)
;

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE `COMPRAS` ADD CONSTRAINT `FK_COMPRAS_COMERCIOS` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `COMERCIOS` (`ID_COMERCIO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `COMPRAS` ADD CONSTRAINT `FK_COMPRAS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `CLIENTES` (`ID_CLIENTE`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `CANJES` ADD CONSTRAINT `FK_CANJES_COMERCIOS` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `COMERCIOS` (`ID_COMERCIO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `CANJES` ADD CONSTRAINT `FK_CANJES_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `CLIENTES` (`ID_CLIENTE`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PUNTOS` ADD CONSTRAINT `FK_PUNTOS_COMERCIO` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `COMERCIOS` (`ID_COMERCIO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PUNTOS` ADD CONSTRAINT `FK_PUNTOS_CLIENTE` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `CLIENTES` (`ID_CLIENTE`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

