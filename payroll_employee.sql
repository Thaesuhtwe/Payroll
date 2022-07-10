-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: payroll
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(45) NOT NULL,
  `emp_phone` varchar(45) NOT NULL,
  `emp_email` varchar(45) NOT NULL,
  `emp_address` varchar(45) NOT NULL,
  `emp_gender` varchar(45) NOT NULL,
  `emp_dob` date DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `position_id` int DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `position_id_idx` (`position_id`),
  CONSTRAINT `position_id` FOREIGN KEY (`position_id`) REFERENCES `position` (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (6,'thae','123456','thae@gmail.com','ygn','female',NULL,NULL,NULL,NULL,NULL,NULL),(7,'su','564566','su@gmail.com','mdy','female',NULL,NULL,NULL,NULL,NULL,NULL),(8,'mya','123333','afdd','afdsdd','afdsd',NULL,NULL,NULL,NULL,NULL,NULL),(9,'su','12333','su@gmail.com','mdy','female',NULL,NULL,NULL,NULL,NULL,NULL),(10,'wah','123333','wah@gamil.com','ygn','female',NULL,NULL,NULL,NULL,NULL,NULL),(11,'may','456789','may@gmail.com','ygn','female',NULL,NULL,NULL,NULL,NULL,NULL),(12,'tt','123333','tt@gmail.com','mdy','female',NULL,NULL,NULL,NULL,NULL,NULL),(13,'ttt','11111','ttttt','fddgsf','fdsff',NULL,NULL,NULL,NULL,NULL,NULL),(14,'ertt','7585888','errtrt','rteerr','rewtret',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-08 15:49:25
