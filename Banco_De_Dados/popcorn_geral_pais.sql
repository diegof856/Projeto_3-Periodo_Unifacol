CREATE DATABASE  IF NOT EXISTS `popcorn_geral` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `popcorn_geral`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: popcorn_geral
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id_pais` int unsigned NOT NULL AUTO_INCREMENT,
  `pais` varchar(45) NOT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Abecásia'),(2,'Afeganistão'),(3,'África do Sul'),(4,'Albânia'),(5,'Alemanha'),(6,'Andorra'),(7,'Angola'),(8,'Antígua e Barbuda'),(9,'Arábia Saudita'),(10,'Argélia'),(11,'Argentina'),(12,'Armênia'),(13,'Austrália'),(14,'Áustria'),(15,'Azerbaijão'),(16,'Bahamas'),(17,'Bahrein'),(18,'Bangladesh'),(19,'Barbados'),(20,'Bélgica'),(21,'Belize'),(22,'Benim'),(23,'Bielorrússia'),(24,'Bolívia'),(25,'Bósnia e Herzegovina'),(26,'Botswana'),(27,'Brasil'),(28,'Brunei'),(29,'Bulgária'),(30,'Burkina Faso'),(31,'Burundi'),(32,'Butão'),(33,'Cabo Verde'),(34,'Camarões'),(35,'Camboja'),(36,'Canadá'),(37,'Catar'),(38,'Cazaquistão'),(39,'Chade'),(40,'Chile'),(41,'China'),(42,'Chipre'),(43,'Cingapura'),(44,'Colômbia'),(45,'Comores'),(46,'Congo'),(47,'Coreia do Norte'),(48,'Coreia do Sul'),(49,'Costa do Marfim'),(50,'Costa Rica'),(51,'Croácia'),(52,'Cuba'),(53,'Dinamarca'),(54,'Djibouti'),(55,'Dominica'),(56,'Egito'),(57,'El Salvador'),(58,'Equador'),(59,'Eritreia'),(60,'Escócia'),(61,'Eslováquia'),(62,'Eslovênia'),(63,'Espanha'),(64,'Estônia'),(65,'Eswatini'),(66,'Etiópia'),(67,'Fiji'),(68,'Filipinas'),(69,'Finlândia'),(70,'França'),(71,'Gabão'),(72,'Gâmbia'),(73,'Gana'),(74,'Geórgia'),(75,'Granada'),(76,'Grécia'),(77,'Guatemala'),(78,'Guiana'),(79,'Guiné'),(80,'Guiné-Bissau'),(81,'Guiné Equatorial'),(82,'Haiti'),(83,'Holanda'),(84,'Honduras'),(85,'Hungria'),(86,'Iêmen'),(87,'Índia'),(88,'Indonésia'),(89,'Inglaterra'),(90,'Irã'),(91,'Iraque'),(92,'Irlanda do Norte'),(93,'Irlanda'),(94,'Islândia'),(95,'Israel'),(96,'Itália'),(97,'Jamaica'),(98,'Japão'),(99,'Jordânia'),(100,'Kiribati'),(101,'Kosovo'),(102,'Kuwait'),(103,'Laos'),(104,'Lesoto'),(105,'Letônia'),(106,'Líbano'),(107,'Libéria'),(108,'Líbia'),(109,'Liechtenstein'),(110,'Lituânia'),(111,'Luxemburgo'),(112,'Macedônia do Norte'),(113,'Madagascar'),(114,'Malásia'),(115,'Malawi'),(116,'Maldivas'),(117,'Mali'),(118,'Malta'),(119,'Marrocos'),(120,'Marshall'),(121,'Maurícia'),(122,'Mauritânia'),(123,'México'),(124,'Mianmar'),(125,'Micronésia'),(126,'Moçambique'),(127,'Moldávia'),(128,'Mônaco'),(129,'Mongólia'),(130,'Montenegro'),(131,'Namíbia'),(132,'Nauru'),(133,'Nepal'),(134,'Nicarágua'),(135,'Níger'),(136,'Nigéria'),(137,'Noruega'),(138,'Nova Zelândia'),(139,'Omã'),(140,'Ossétia do Sul'),(141,'País de Gales'),(142,'Países Baixos'),(143,'Palau'),(144,'Palestina'),(145,'Panamá'),(146,'Papua-Nova Guiné'),(147,'Paquistão'),(148,'Paraguai'),(149,'Peru'),(150,'Polônia'),(151,'Portugal'),(152,'Qatar'),(153,'Quênia'),(154,'Quirguistão'),(155,'Quiribati'),(156,'Reino Unido'),(157,'República do Congo'),(158,'República Dominicana'),(159,'República Tcheca'),(160,'Romênia'),(161,'Ruanda'),(162,'Rússia'),(163,'Salomão'),(164,'Samoa'),(165,'San Marino'),(166,'Santa Lúcia'),(167,'São Tomé e Príncipe'),(168,'Senegal'),(169,'Serra Leoa'),(170,'Sérvia'),(171,'Seychelles'),(172,'Singapura'),(173,'Síria'),(174,'Somália'),(175,'Sri Lanka'),(176,'Suazilândia'),(177,'Sudão do Sul'),(178,'Sudão'),(179,'Suécia'),(180,'Suíça'),(181,'Suriname'),(182,'Tailândia'),(183,'Taiwan'),(184,'Tajiquistão'),(185,'Tanzânia'),(187,'Timor-Leste'),(188,'Togo'),(189,'Tonga'),(190,'Trinidad'),(191,'Tobago'),(192,'Tunísia'),(193,'Turcomenistão'),(194,'Turquia'),(195,'Tuvalu'),(196,'Ucrânia'),(197,'Uganda'),(198,'Uruguai'),(199,'Uzbequistão'),(200,'Vanuatu'),(201,'Vaticano'),(202,'Venezuela'),(203,'Vietnã'),(204,'Zâmbia'),(205,'Zimbábue'),(206,'Emirados Árabes'),(207,'Estados Federados da'),(208,'Estados Unidos da Am'),(209,'República Árabe Saar'),(210,'República Centro-Afr'),(211,'República Democrátic'),(212,'República Turca de C'),(213,'São Cristóvão e Névi'),(214,'São Vicente e Granad');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-31 19:53:48
