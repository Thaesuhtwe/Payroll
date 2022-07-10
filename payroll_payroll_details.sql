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
-- Table structure for table `payroll_details`
--

DROP TABLE IF EXISTS `payroll_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll_details` (
  `payroll_details_id` int NOT NULL,
  `late` datetime NOT NULL,
  `salary` double NOT NULL,
  `allowance_amount` double NOT NULL,
  `deduction_amount` double NOT NULL,
  `net_salary` double NOT NULL,
  `allowance_id` int NOT NULL,
  `deduction_id` int NOT NULL,
  `payroll_id` int NOT NULL,
  `emp_id` int NOT NULL,
  PRIMARY KEY (`payroll_details_id`),
  KEY `FK_deduction_id_idx` (`deduction_id`),
  KEY `FK_allowance_id_idx` (`allowance_id`),
  KEY `FK_payroll_id_idx` (`payroll_id`),
  KEY `FK-emp_id_idx` (`emp_id`),
  CONSTRAINT `FK-emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `FK_allowance_id` FOREIGN KEY (`allowance_id`) REFERENCES `allowance` (`allowance_id`),
  CONSTRAINT `FK_deduction_id` FOREIGN KEY (`deduction_id`) REFERENCES `deduction` (`deduction_id`),
  CONSTRAINT `FK_payroll_id` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`payroll_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll_details`
--

LOCK TABLES `payroll_details` WRITE;
/*!40000 ALTER TABLE `payroll_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-08 15:49:24
