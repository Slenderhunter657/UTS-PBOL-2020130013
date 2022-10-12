-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: data_simulasi_pet
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `player_id` char(4) NOT NULL,
  `item_id` char(4) NOT NULL,
  `item_count` int DEFAULT NULL,
  PRIMARY KEY (`player_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('p001','f001',12),('p001','f003',1),('p002','f001',5);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` char(4) NOT NULL,
  `item_name` varchar(20) DEFAULT NULL,
  `item_type` int DEFAULT NULL,
  `rarity` float DEFAULT NULL,
  `add_hunger` float DEFAULT NULL,
  `add_happy` float DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('f001','Cookie',1,0.5,10,5),('f002','Sandwich',1,0.25,20,10),('f003','Cake',1,0.15,40,20),('f004','Ice Cream',1,0.1,20,40),('t001','Yoyo',2,0.6,0,20),('t002','Top',2,0.2,0,25),('t003','Plushie',2,0.15,0,30),('t004','Figurine',2,0.05,0,40);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_base`
--

DROP TABLE IF EXISTS `pet_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_base` (
  `pet_base_id` varchar(4) NOT NULL,
  `pet_type` int DEFAULT NULL,
  `max_hunger` float DEFAULT NULL,
  `max_happy` float DEFAULT NULL,
  PRIMARY KEY (`pet_base_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_base`
--

LOCK TABLES `pet_base` WRITE;
/*!40000 ALTER TABLE `pet_base` DISABLE KEYS */;
INSERT INTO `pet_base` VALUES ('pb01',1,150,100),('pb02',2,200,120),('pb03',3,300,100);
/*!40000 ALTER TABLE `pet_base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_history`
--

DROP TABLE IF EXISTS `pet_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_history` (
  `player_id` char(4) NOT NULL,
  `pet_id` char(4) NOT NULL,
  `pet_name` varchar(20) DEFAULT NULL,
  `age` float DEFAULT NULL,
  `training` float DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`player_id`,`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_history`
--

LOCK TABLES `pet_history` WRITE;
/*!40000 ALTER TABLE `pet_history` DISABLE KEYS */;
INSERT INTO `pet_history` VALUES ('p002','p002','test2',10,30.2,2);
/*!40000 ALTER TABLE `pet_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_instance`
--

DROP TABLE IF EXISTS `pet_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_instance` (
  `pet_id` char(4) NOT NULL,
  `player_id` char(4) NOT NULL,
  `pet_base_id` char(4) NOT NULL,
  `pet_name` varchar(20) DEFAULT NULL,
  `cur_health` float DEFAULT NULL,
  `cur_hunger` float DEFAULT NULL,
  `cur_happy` float DEFAULT NULL,
  `cur_train` float DEFAULT NULL,
  `cur_age` float DEFAULT NULL,
  `cur_exp` float DEFAULT NULL,
  `cur_level` int DEFAULT NULL,
  PRIMARY KEY (`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_instance`
--

LOCK TABLES `pet_instance` WRITE;
/*!40000 ALTER TABLE `pet_instance` DISABLE KEYS */;
INSERT INTO `pet_instance` VALUES ('p001','p001','pb01','test1',100,50,50,12,5,15,4),('p003','p002','pb03','test3',100,300,100,30,4,2,6);
/*!40000 ALTER TABLE `pet_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `player_id` char(4) NOT NULL,
  `player_name` varchar(20) DEFAULT NULL,
  `reset_count` int DEFAULT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES ('p001','dummy1',0),('p002','dummy2',1);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-10 21:01:01
