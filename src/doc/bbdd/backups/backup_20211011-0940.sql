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
-- Table structure for table `canjes`
--

DROP TABLE IF EXISTS `canjes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canjes` (
  `ID_CANJE` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA_CANJE` date DEFAULT NULL,
  `PUNTOS` int(11) NOT NULL,
  `ID_COMERCIO` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CANJE`),
  KEY `FK_CANJES_COMERCIOS` (`ID_COMERCIO`),
  KEY `FK_CANJES_CLIENTES` (`ID_CLIENTE`),
  CONSTRAINT `FK_CANJES_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_CANJES_COMERCIOS` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `comercios` (`ID_COMERCIO`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canjes`
--

LOCK TABLES `canjes` WRITE;
/*!40000 ALTER TABLE `canjes` DISABLE KEYS */;
INSERT INTO `canjes` VALUES (6,'2021-10-04',2,1,1),(7,'2021-10-04',2,2,2),(8,'2021-10-04',1,1,1),(9,'2021-10-04',1,1,1);
/*!40000 ALTER TABLE `canjes` ENABLE KEYS */;
UNLOCK TABLES;

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
  `ID_USUARIO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`),
  KEY `FK_CLIENTES_USUARIOS` (`ID_USUARIO`),
  CONSTRAINT `FK_CLIENTES_USUARIOS` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'W3T','Ana María','Gordo','Roca','1234567C','913654321',3),(2,'D4R','Pedro','Delgado','Balán','2315487J','913216565',4),(3,'H6G','Adela','Sánchez','Garamendi','51988965Z','654987954',NULL);
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
  `ID_USUARIO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_COMERCIO`),
  KEY `FK_COMERCIOS_USUARIOS` (`ID_USUARIO`),
  CONSTRAINT `FK_COMERCIOS_USUARIOS` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercios`
--

LOCK TABLES `comercios` WRITE;
/*!40000 ALTER TABLE `comercios` DISABLE KEYS */;
INSERT INTO `comercios` VALUES (1,'A1A','CervePub','B567890','Antonio','López','Miralles','C/ de la Puerta Vieja, 22 8º A','Madrid','28038',1),(2,'A38','Librería La Luz','B234561','Sara','Lix','Pondedu','Avda. de la Albufera 245, 7º G','Madrid','28038',2),(3,'Z24','Perfumería Jazmín','B123123','Magdalena','Pontón','Márquez','c/ Puerto de Cumbre Vieja 26, 2º E','Rivas-Vaciamadrid','28512',NULL);
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
  `ID_COMERCIO` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_COMPRA`),
  KEY `FK_COMPRAS_COMERCIOS` (`ID_COMERCIO`),
  KEY `FK_COMPRAS_CLIENTES` (`ID_CLIENTE`),
  CONSTRAINT `FK_COMPRAS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_COMPRAS_COMERCIOS` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `comercios` (`ID_COMERCIO`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (12,'2021-10-04',4,1,1),(13,'2021-10-04',3,1,1),(14,'2021-10-04',2,2,1),(15,'2021-10-04',4,2,2),(16,'2021-10-08',3,1,1);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puntos`
--

DROP TABLE IF EXISTS `puntos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puntos` (
  `ID_PUNTO` int(11) NOT NULL AUTO_INCREMENT,
  `TOTAL` int(11) NOT NULL,
  `ID_COMERCIO` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PUNTO`),
  KEY `FK_PUNTOS_COMERCIO` (`ID_COMERCIO`),
  KEY `FK_PUNTOS_CLIENTE` (`ID_CLIENTE`),
  CONSTRAINT `FK_PUNTOS_CLIENTE` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_PUNTOS_COMERCIO` FOREIGN KEY (`ID_COMERCIO`) REFERENCES `comercios` (`ID_COMERCIO`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puntos`
--

LOCK TABLES `puntos` WRITE;
/*!40000 ALTER TABLE `puntos` DISABLE KEYS */;
INSERT INTO `puntos` VALUES (2,6,1,1),(3,2,2,1),(4,2,2,2);
/*!40000 ALTER TABLE `puntos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID_ROL` int(11) NOT NULL,
  `NOMBRE_ROL` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_ROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'COMERCIO'),(3,'CLIENTE');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_usuarios`
--

DROP TABLE IF EXISTS `roles_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_usuarios` (
  `ID_USUARIO` int(11) NOT NULL,
  `ID_ROL` int(11) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`,`ID_ROL`),
  KEY `FK_ROLS_USU_ROLES` (`ID_ROL`),
  CONSTRAINT `FK_ROLS_USU_ROLES` FOREIGN KEY (`ID_ROL`) REFERENCES `roles` (`ID_ROL`),
  CONSTRAINT `FK_ROLS_USU_USUARIOS` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_usuarios`
--

LOCK TABLES `roles_usuarios` WRITE;
/*!40000 ALTER TABLE `roles_usuarios` DISABLE KEYS */;
INSERT INTO `roles_usuarios` VALUES (5,1),(1,2),(2,2),(3,3),(4,3);
/*!40000 ALTER TABLE `roles_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `ID_USUARIO` int(11) NOT NULL,
  `LOGIN` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CAMBIAR_PASSWORD` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'a','1','0'),(2,'b','1','0'),(3,'c','1','0'),(4,'d','1','0'),(5,'z','1','0');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-11  9:40:49
