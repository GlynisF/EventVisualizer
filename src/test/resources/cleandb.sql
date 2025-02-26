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
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `date_of_event` date DEFAULT NULL,
                          `start_time` time DEFAULT NULL,
                          `end_time` time DEFAULT NULL,
                          `description` text,
                          `event_id` int DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `details_pk_2` (`id`),
                          KEY `details_event_fk` (`event_id`),
                          CONSTRAINT `details_event_fk` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES
                         (1,'2025-03-01','21:00','02:00','A deep dive into underground electronic beats, where the bass reverberates through the night.',1),
                         (2,'2025-03-02','22:00','03:00','A journey through pulsating basslines and immersive soundscapes.',2),
                         (3,'2025-03-03','20:30','01:30','A vibrant mix of neon lights and electrifying synth-driven melodies.',3),
                         (4,'2025-03-04','21:00','02:00','A high-energy event where rhythm takes over and the dancefloor erupts.',4),
                         (5,'2025-03-05','22:00','03:30','Exploring the depths of bass music with subsonic vibrations.',5),
                         (6,'2025-03-06','20:00','01:00','A fusion of melody and chaos, creating a euphoric musical experience.',6),
                         (7,'2025-03-07','21:30','02:30','A glowing spectacle of sound and light in an immersive rave atmosphere.',7),
                         (8,'2025-03-08','23:00','04:00','A ceremonial experience of low-end frequencies and hypnotic rhythms.',8),
                         (9,'2025-03-09','20:00','00:30','Dreamy, otherworldly beats that transport you to a higher state of mind.',9),
                         (10,'2025-03-10','22:00','03:00','A late-night session filled with deep house, techno, and beyond.',10),
                         (11,'2025-03-11','21:00','01:30','An electrifying mix of high-voltage sounds and energetic performances.',11),
                         (12,'2025-03-12','22:30','02:30','A rhythmic blend of house, techno, and breakbeats for continuous movement.',12),
                         (13,'2025-03-13','20:30','00:30','A cosmic journey through celestial soundscapes and harmonized rhythms.',13),
                         (14,'2025-03-14','22:00','03:00','A sacred space where basslines reign supreme in a grand sonic cathedral.',14),
                         (15,'2025-03-15','21:30','02:00','Shifting through sonic frequencies to redefine the club experience.',15),
                         (16,'2025-03-16','22:00','03:30','Illusions of sound blend into a seamless mirage of electronic bliss.',16),
                         (17,'2025-03-17','23:00','04:00','Where echoes of past and future sounds collide in perfect harmony.',17),
                         (18,'2025-03-18','21:00','02:00','A heartbeat of sound that drives the night forward.',18),
                         (19,'2025-03-19','23:30','04:30','A resonating experience that keeps the midnight energy alive.',19),
                         (20,'2025-03-20','22:00','03:00','A symphony of electronic sounds orchestrated for peak energy.',20),
                         (21,'2025-03-21','20:30','01:30','A dreamlike neon-lit dance experience with immersive beats.',21),
                         (22,'2025-03-22','21:00','02:30','Exploring multiple realms of resonance through sound.',22),
                         (23,'2025-03-23','22:00','03:30','A whirlwind of sonic textures, creating an unstoppable dance vortex.',23),
                         (24,'2025-03-24','18:00','01:00','A multi-artist festival exploring the full spectrum of sound.',24),
                         (25,'2025-03-25','22:00','03:30','A dark yet immersive dreamscape of futuristic electronic music.',24),
                         (26,'2025-03-26','21:30','02:00','Sonic frequencies beyond the visible spectrum, designed to move you.',26),
                         (27,'2025-03-27','20:00','00:30','A moonlit dance under the stars with ethereal grooves.',27),
                         (28,'2025-03-28','22:00','03:30','Big boom boom energy with bass-heavy beats.',28),
                         (29,'2025-03-29','23:00','04:00','A euphoric blend of digital-age sounds and uplifting melodies.',29),
                         (30,'2025-03-30','21:30','02:30','A balance of harmony and chaos, distorting expectations.',30),
                         (31,'2025-03-31','22:00','03:00','Robotic grooves and futuristic techno for the cyber-dancers.',31),
                         (32,'2025-04-01','20:30','01:30','High-energy vibes to elevate the crowd’s spirit.',32),
                         (33,'2025-04-02','22:00','03:00','Past and future sounds merge into an unforgettable sonic experience.',33),
                         (34,'2025-04-03','23:00','04:00','A forward-thinking sonic assault on the senses.',34),
                         (35,'2025-01-01','21:00','02:00','Sounds from the underground',15);
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;
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
                            `user_id` int NOT NULL,
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
INSERT INTO `notebook` VALUES (1,'October Events',1),(2,'Birthday Party',2),(3,'Gamma Ray Event',3),(4,'Surprise Party',4),(5,'November Shows',5),(6,'New Year\'s Eve',6),(7,'High Noon Show',1),(8,'December Events',2),(9,'Halloween 2024',3),(10,'Mom\'s Birthday',4),(11,'Sylvee Show',5),(12,'Valentine\'s Day Show',6);
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
                        `email` varchar(50) NOT NULL,
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
INSERT INTO `user` VALUES (1,'Joe','Coyne','jcoyne@email.com','jcoyne','supersecret1','1964-04-01'),(2,'Fred','Hensen','frhen@email.com','fhensen','supersecret2','1988-05-08'),(3,'Barney','Curry','bcurry@email.com','bcurry','supersecret3','1947-11-11'),(4,'Karen','Mack','karenM@email.com','kmack','supersecret4','1986-07-08'),(5,'Dianne','Klein','DK@email.com','dklein','supersecret5','1991-01-22'),(6,'Dawn','Tillman','dawntill@email.com','dtillman','supersecret6','1979-08-30');
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

-- Dump completed on 2025-02-25 15:28:30