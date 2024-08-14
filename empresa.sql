-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: empresa
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'jefe'),(2,'Jefe de Proyecto de java editado'),(3,'programador'),(4,'limpiador'),(5,'conductor'),(6,'director'),(8,'jefe de proyecto de java'),(9,'jefe de alho'),(10,'jefe de proyecto modificado'),(11,'jefe de proyecto modificado'),(12,'jefe de proyecto modificado'),(13,'jefe de proyecto modificado'),(20,'subido desde rest'),(21,'cargo desde rest'),(22,'cargo desde rest 2'),(23,'cargo desde rest 3'),(24,'cargo desde rest 4'),(28,'Jefe de Proyecto de java de prueba1'),(31,'Jefe de Proyecto de java de prueba1'),(34,'Jefe de Proyecto de java de prueba1');
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_laborales`
--

DROP TABLE IF EXISTS `datos_laborales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_laborales` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SALARIO` int NOT NULL,
  `CARGOS_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_DATOS_LABORALES_CARGOS_idx` (`CARGOS_ID`),
  CONSTRAINT `fk_DATOS_LABORALES_CARGOS` FOREIGN KEY (`CARGOS_ID`) REFERENCES `cargos` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_laborales`
--

LOCK TABLES `datos_laborales` WRITE;
/*!40000 ALTER TABLE `datos_laborales` DISABLE KEYS */;
INSERT INTO `datos_laborales` VALUES (1,2000,1),(2,4260,5),(6,3000,4),(7,3000,4),(8,3000,4),(9,3000,4),(10,3000,4),(11,3000,4),(12,3000,4),(13,3000,4),(14,3000,4),(15,3000,4),(16,3000,4),(17,3000,4),(18,3000,4),(19,3000,4),(20,3000,4),(21,3000,4),(22,3000,4),(23,3000,4);
/*!40000 ALTER TABLE `datos_laborales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_personales`
--

DROP TABLE IF EXISTS `datos_personales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_personales` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `HIJOS_ID` int NOT NULL,
  `ESTADOS_CIVILES_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_DATOS_PERSONALES_ESTADOS_CIVILES1_idx` (`ESTADOS_CIVILES_ID`),
  KEY `fk_DATOS_PERSONALES_HIJOS1_idx` (`HIJOS_ID`),
  CONSTRAINT `fk_DATOS_PERSONALES_ESTADOS_CIVILES1` FOREIGN KEY (`ESTADOS_CIVILES_ID`) REFERENCES `estados_civiles` (`ID`),
  CONSTRAINT `fk_DATOS_PERSONALES_HIJOS1` FOREIGN KEY (`HIJOS_ID`) REFERENCES `hijos` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_personales`
--

LOCK TABLES `datos_personales` WRITE;
/*!40000 ALTER TABLE `datos_personales` DISABLE KEYS */;
INSERT INTO `datos_personales` VALUES (1,2,3),(2,1,2),(3,3,4),(4,4,5),(5,5,6),(6,6,7);
/*!40000 ALTER TABLE `datos_personales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(100) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `TELEFONO` varchar(11) NOT NULL,
  `FECHA_NACIMIENTO` datetime NOT NULL,
  `FECHA_ALTA` datetime NOT NULL,
  `FECHA_BAJA` datetime DEFAULT NULL,
  `DATOS_PERSONALES_ID` int NOT NULL,
  `DATOS_LABORALES_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_EMPLEADOS_DATOS_PERSONALES1_idx` (`DATOS_PERSONALES_ID`),
  KEY `fk_EMPLEADOS_DATOS_LABORALES1_idx` (`DATOS_LABORALES_ID`),
  CONSTRAINT `fk_EMPLEADOS_DATOS_LABORALES1` FOREIGN KEY (`DATOS_LABORALES_ID`) REFERENCES `datos_laborales` (`ID`),
  CONSTRAINT `fk_EMPLEADOS_DATOS_PERSONALES1` FOREIGN KEY (`DATOS_PERSONALES_ID`) REFERENCES `datos_personales` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'pepe','41008046N','666666666','1991-02-26 00:00:00','2021-09-20 00:00:00',NULL,2,1),(2,'vitor','20006446T','654654654','1990-05-26 00:00:00','2020-05-18 00:00:00',NULL,1,2),(3,'empleado2','22222','1234','1999-04-22 00:00:00','2021-04-22 00:00:00',NULL,1,1),(4,'empleado2','22222','1234','1999-04-22 00:00:00','2021-04-22 00:00:00',NULL,1,1),(5,'empleado2','22222','1234','1999-04-22 00:00:00','2021-04-22 00:00:00',NULL,1,1);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados_civiles`
--

DROP TABLE IF EXISTS `estados_civiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados_civiles` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados_civiles`
--

LOCK TABLES `estados_civiles` WRITE;
/*!40000 ALTER TABLE `estados_civiles` DISABLE KEYS */;
INSERT INTO `estados_civiles` VALUES (1,'soltero'),(2,'prometido'),(3,'viudo'),(4,'divorciado'),(5,'divorciado'),(6,'divorciado'),(7,'divorciado'),(8,'viudo'),(10,'viudo'),(12,'viudo');
/*!40000 ALTER TABLE `estados_civiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hijos`
--

DROP TABLE IF EXISTS `hijos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hijos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CHICOS` int DEFAULT NULL,
  `CHICAS` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hijos`
--

LOCK TABLES `hijos` WRITE;
/*!40000 ALTER TABLE `hijos` DISABLE KEYS */;
INSERT INTO `hijos` VALUES (1,1,3),(2,90,0),(3,5,3),(4,5,3),(5,5,3),(6,5,3),(7,11,14),(9,11,14),(11,11,14);
/*!40000 ALTER TABLE `hijos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Moderador'),(2,'Algo');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USUARIO` varchar(45) NOT NULL,
  `CLAVE` varchar(255) NOT NULL,
  `ENABLE` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'angy','1234',NULL),(2,'doggo','4321',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_has_roles`
--

DROP TABLE IF EXISTS `usuarios_has_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_has_roles` (
  `USUARIOS_ID` int NOT NULL,
  `ROLES_ID` int NOT NULL,
  PRIMARY KEY (`USUARIOS_ID`,`ROLES_ID`),
  KEY `fk_USUARIOS_has_ROLES_ROLES1_idx` (`ROLES_ID`),
  KEY `fk_USUARIOS_has_ROLES_USUARIOS1_idx` (`USUARIOS_ID`),
  CONSTRAINT `fk_USUARIOS_has_ROLES_ROLES1` FOREIGN KEY (`ROLES_ID`) REFERENCES `roles` (`ID`),
  CONSTRAINT `fk_USUARIOS_has_ROLES_USUARIOS1` FOREIGN KEY (`USUARIOS_ID`) REFERENCES `usuarios` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_has_roles`
--

LOCK TABLES `usuarios_has_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_has_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_has_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-06 10:05:46
