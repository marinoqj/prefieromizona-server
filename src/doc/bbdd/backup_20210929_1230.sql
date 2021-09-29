-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: prefieromizona
-- ------------------------------------------------------
-- Server version	5.7.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `COD_CLIENTE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `NOMBRE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APELLIDO1` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APELLIDO2` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DNI` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TELEFONO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'A1','Rosa','Luengo','Salmones','12345467L','654654654'),(2,'A2','Jacinta','Raboso','Molina','22223333Q','654321654');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercios`
--

DROP TABLE IF EXISTS `comercios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercios` (
  `ID_COMERCIO` int(11) NOT NULL AUTO_INCREMENT,
  `COD_COMERCIO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RAZON_SOCIAL` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CIF` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `NOMBRE_RESPONSABLE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APELLIDO1_RESPONSABLE` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `APELLIDO2_RESPONSABLE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DIRECCION` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `MUNICIPIO` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `COD_POSTAL` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID_COMERCIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercios`
--

LOCK TABLES `comercios` WRITE;
/*!40000 ALTER TABLE `comercios` DISABLE KEYS */;
INSERT INTO `comercios` VALUES (1,'1','aa','qqq','a','a','a','a','a','1');
/*!40000 ALTER TABLE `comercios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `ID_COMPRA` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA_COMPRA` date NOT NULL,
  `PUNTOS` int(11) NOT NULL,
  `FECHA_CANJE` date DEFAULT NULL,
  `ID_COMERCIO` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_COMPRA`),
  KEY `FK_PUNTOS_COMERCIOS` (`ID_COMERCIO`),
  KEY `FK_PUNTOS_CLIENTES` (`ID_CLIENTE`),
  CONSTRAINT `FK_PUNTOS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_PUNTOS_COMERCIOS` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `comercios` (`ID_COMERCIO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,'2020-02-22',15,'2020-02-25',1,1);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-29 12:30:30
