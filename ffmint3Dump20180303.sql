-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fullfillmint3
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `idactivity` int(11) NOT NULL AUTO_INCREMENT,
  `activity_date` date DEFAULT NULL,
  `activity_location` varchar(45) DEFAULT NULL,
  `activity_theme` varchar(45) DEFAULT NULL,
  `goal_history_idgoal` int(11) NOT NULL,
  `step*` varchar(45) DEFAULT NULL,
  `category_idcategory` int(11) NOT NULL,
  PRIMARY KEY (`idactivity`,`goal_history_idgoal`,`category_idcategory`),
  KEY `fk_activity_goal_history1_idx` (`goal_history_idgoal`),
  KEY `fk_activity_category1_idx` (`category_idcategory`),
  CONSTRAINT `fk_activity_category1` FOREIGN KEY (`category_idcategory`) REFERENCES `category` (`idcategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_goal_history1` FOREIGN KEY (`goal_history_idgoal`) REFERENCES `goal_history` (`idgoal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;

--
-- Table structure for table `activity_level`
--

DROP TABLE IF EXISTS `activity_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_level` (
  `idactivity_level` int(11) NOT NULL AUTO_INCREMENT,
  `beginner` varchar(45) DEFAULT NULL,
  `intermediate` varchar(45) DEFAULT NULL,
  `advanced` varchar(45) DEFAULT NULL,
  `pro` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idactivity_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_level`
--

/*!40000 ALTER TABLE `activity_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_level` ENABLE KEYS */;

--
-- Table structure for table `activity_level_has_activity`
--

DROP TABLE IF EXISTS `activity_level_has_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_level_has_activity` (
  `activity_level_idactivity_level` int(11) NOT NULL,
  `activity_idactivity` int(11) NOT NULL,
  PRIMARY KEY (`activity_level_idactivity_level`,`activity_idactivity`),
  KEY `fk_activity_level_has_activity_activity1_idx` (`activity_idactivity`),
  KEY `fk_activity_level_has_activity_activity_level1_idx` (`activity_level_idactivity_level`),
  CONSTRAINT `fk_activity_level_has_activity_activity1` FOREIGN KEY (`activity_idactivity`) REFERENCES `activity` (`idactivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_level_has_activity_activity_level1` FOREIGN KEY (`activity_level_idactivity_level`) REFERENCES `activity_level` (`idactivity_level`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_level_has_activity`
--

/*!40000 ALTER TABLE `activity_level_has_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_level_has_activity` ENABLE KEYS */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `idcategory` int(11) NOT NULL AUTO_INCREMENT,
  `category_travel` varchar(45) DEFAULT NULL,
  `category_health` varchar(45) DEFAULT NULL,
  `category_lifeMilestone` varchar(45) DEFAULT NULL,
  `category_educNsk` varchar(45) DEFAULT NULL,
  `category_adventure` varchar(60) DEFAULT NULL,
  `category_outdoors` varchar(45) DEFAULT NULL,
  `category_entertainment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

--
-- Table structure for table `competed_goal`
--

DROP TABLE IF EXISTS `competed_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competed_goal` (
  `idcompeted_goal` int(11) NOT NULL AUTO_INCREMENT,
  `goal_title` varchar(45) DEFAULT NULL,
  `goal_history_idgoal` int(11) NOT NULL,
  PRIMARY KEY (`idcompeted_goal`),
  KEY `fk_competed_goal_goal_history1_idx` (`goal_history_idgoal`),
  CONSTRAINT `fk_competed_goal_goal_history1` FOREIGN KEY (`goal_history_idgoal`) REFERENCES `goal_history` (`idgoal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competed_goal`
--

/*!40000 ALTER TABLE `competed_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `competed_goal` ENABLE KEYS */;

--
-- Table structure for table `goal_history`
--

DROP TABLE IF EXISTS `goal_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_history` (
  `idgoal` int(11) NOT NULL AUTO_INCREMENT,
  `goal_title` varchar(45) DEFAULT NULL,
  `goal_targetDate` date DEFAULT NULL,
  `goal_startDatel` date DEFAULT NULL,
  `goal_description` multilinestring DEFAULT NULL,
  `goal_accPartner` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idgoal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_history`
--

/*!40000 ALTER TABLE `goal_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `goal_history` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `user_first_name` varchar(45) DEFAULT NULL,
  `user_last_name` varchar(45) DEFAULT NULL,
  `user_email` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=4867 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`iduser`, `user_first_name`, `user_last_name`, `user_email`) VALUES (4866,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `user_has_category`
--

DROP TABLE IF EXISTS `user_has_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_category` (
  `user_iduser` int(11) NOT NULL,
  `category_idcategory` int(11) NOT NULL,
  PRIMARY KEY (`user_iduser`,`category_idcategory`),
  KEY `fk_user_has_category_category1_idx` (`category_idcategory`),
  KEY `fk_user_has_category_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_category_category1` FOREIGN KEY (`category_idcategory`) REFERENCES `category` (`idcategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_category_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_category`
--

/*!40000 ALTER TABLE `user_has_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_has_category` ENABLE KEYS */;

--
-- Table structure for table `user_has_goal_history`
--

DROP TABLE IF EXISTS `user_has_goal_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_goal_history` (
  `user_iduser` int(11) NOT NULL,
  `goal_history_idgoal` int(11) NOT NULL,
  PRIMARY KEY (`user_iduser`,`goal_history_idgoal`),
  KEY `fk_user_has_goal_history_goal_history1_idx` (`goal_history_idgoal`),
  KEY `fk_user_has_goal_history_user_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_goal_history_goal_history1` FOREIGN KEY (`goal_history_idgoal`) REFERENCES `goal_history` (`idgoal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_goal_history_user` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_goal_history`
--

/*!40000 ALTER TABLE `user_has_goal_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_has_goal_history` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-03  9:59:09
