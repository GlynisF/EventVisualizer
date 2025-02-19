-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: ev_test
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `event_name` varchar(100) NOT NULL,
                         `notebook_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `event_pk_2` (`id`),
                         KEY `event_notebook_fk` (`notebook_id`),
                         CONSTRAINT `event_notebook_fk` FOREIGN KEY (`notebook_id`) REFERENCES `notebook` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Echoes of the Underground',1),(2,'Bassline Odyssey',1),(3,'Neon Soundscapes',1),(4,'Rhythm Rebellion',2),(5,'Subsonic Frequencies',2),(6,'Melodic Mayhem',2),(7,'Luminous Rave',3),(8,'Deep Bass Ritual',3),(9,'Ethereal Grooves',3),(10,'Afterdark Sessions',4),(11,'Voltage Vibes',4),(12,'Pulse & Flow',4),(13,'Celestial Harmonies',5),(14,'Bass Cathedral',5),(15,'Frequency Shift',5),(16,'Sonic Mirage',6),(17,'Echo Chamber',6),(18,'Nocturnal Pulse',6),(19,'Midnight Resonance',7),(20,'Electric Symphony',8),(21,'Neon Reverie',9),(22,'Resonant Realms',10),(23,'Vortex Soundwave',11),(25,'Wavelength Festival',1),(26,'Dystopian Dreamscape',2),(27,'Infrared Audio',3),(28,'Moonlight Beats',4),(29,'Sonic Boom Boom',5),(30,'Digital Euphoria',6),(31,'Harmonic Distortion',1),(32,'Cybernetic Rhythms',2),(33,'Uplifted Frequencies',3),(34,'Echoes in Time',4),(35,'Future Shock',5),(36,'Dubwave Experience',6);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notebook`
--

DROP TABLE IF EXISTS `notebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notebook` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `title` varchar(100) NOT NULL,
                            `user_id` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `notebook_pk_2` (`id`),
                            KEY `notebook_fk` (`user_id`),
                            CONSTRAINT `notebook_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notebook`
--

LOCK TABLES `notebook` WRITE;
/*!40000 ALTER TABLE `notebook` DISABLE KEYS */;
INSERT INTO `notebook` VALUES (1,'March 2025 Events',1),(2,'Birthday Party',2),(3,'Gamma Ray Event',3),(4,'Surprise Party',4),(5,'November Shows',5),(6,'New Year\'s Eve',6),(7,'High Noon Show',1),(8,'December Events',2),(9,'Halloween 2024',3),(10,'Mom\'s Birthday',4),(11,'Sylvee Show',5),(12,'Valentine\'s Day Show',6);
/*!40000 ALTER TABLE `notebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(25) NOT NULL,
                        `last_name` varchar(30) NOT NULL,
                        `username` varchar(15) NOT NULL,
                        `password` varchar(30) NOT NULL,
                        `date_of_birth` date NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `users_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Joe','Coyne','jcoyne','supersecret1','1964-04-01'),(2,'Fred','Hensen','fhensen','supersecret2','1988-05-08'),(3,'Barney','Curry','bcurry','supersecret3','1947-11-11'),(4,'Karen','Mack','kmack','supersecret4','1986-07-08'),(5,'Dianne','Klein','dklein','supersecret5','1991-01-22'),(6,'Dawn','Tillman','dtillman','supersecret6','1979-08-30');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-18 19:01:53
