-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: autobuskastanica
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `destinacija`
--

DROP TABLE IF EXISTS `destinacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `destinacija` (
  `destinacijaID` int(11) NOT NULL AUTO_INCREMENT,
  `drzava` varchar(45) NOT NULL,
  `grad` varchar(45) NOT NULL,
  PRIMARY KEY (`destinacijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinacija`
--

LOCK TABLES `destinacija` WRITE;
/*!40000 ALTER TABLE `destinacija` DISABLE KEYS */;
INSERT INTO `destinacija` VALUES (1,'Srbija','Sremska Mitrovica'),(2,'Srbija','Kraljevo'),(3,'Srbija','Vrnjacka Banja'),(4,'Srbija','Zrenjanin'),(5,'Srbija','Novi Sad');
/*!40000 ALTER TABLE `destinacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `karta`
--

DROP TABLE IF EXISTS `karta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `karta` (
  `kartaID` int(11) NOT NULL AUTO_INCREMENT,
  `konacnaCena` int(11) NOT NULL,
  `vrstaKarte` int(11) NOT NULL,
  `datumRezervacije` date DEFAULT NULL,
  `datumProdaje` date DEFAULT NULL,
  `rezervisao` int(11) DEFAULT NULL,
  `prodao` int(11) DEFAULT NULL,
  `mestoPolaska` int(11) NOT NULL,
  `mestoDolaska` int(11) NOT NULL,
  PRIMARY KEY (`kartaID`),
  KEY `vrstaKarte_idx` (`vrstaKarte`),
  KEY `rezervisao_idx` (`rezervisao`),
  KEY `prodao_idx` (`prodao`),
  KEY `mestoPolaska_idx` (`mestoPolaska`),
  KEY `mestoDolaska_idx` (`mestoDolaska`),
  CONSTRAINT `mestoDolaska` FOREIGN KEY (`mestoDolaska`) REFERENCES `stanica` (`stanicaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mestoPolaska` FOREIGN KEY (`mestoPolaska`) REFERENCES `stanica` (`stanicaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prodao` FOREIGN KEY (`prodao`) REFERENCES `korisnik` (`korisnikID`) ON UPDATE CASCADE,
  CONSTRAINT `rezervisao` FOREIGN KEY (`rezervisao`) REFERENCES `korisnik` (`korisnikID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vrstaKarte` FOREIGN KEY (`vrstaKarte`) REFERENCES `vrstakarte` (`vrstaKarteID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karta`
--

LOCK TABLES `karta` WRITE;
/*!40000 ALTER TABLE `karta` DISABLE KEYS */;
INSERT INTO `karta` VALUES (1,100,1,'2019-05-07','2019-05-09',1,2,1,2),(2,200,2,'2019-05-07','2019-05-09',3,2,1,2),(3,100,4,NULL,'2019-05-18',NULL,2,11,12),(4,100,4,'2019-05-18',NULL,3,NULL,1,2),(5,100,4,NULL,'2019-05-18',NULL,2,1,2);
/*!40000 ALTER TABLE `karta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `komentar` (
  `komentarID` int(11) NOT NULL AUTO_INCREMENT,
  `korisnikID` int(11) NOT NULL,
  `prevoznikID` int(11) NOT NULL,
  `komentar` varchar(200) NOT NULL,
  PRIMARY KEY (`komentarID`),
  KEY `korisnikID_idx` (`korisnikID`),
  KEY `prevoznikID_idx` (`prevoznikID`),
  CONSTRAINT `korisnikID` FOREIGN KEY (`korisnikID`) REFERENCES `korisnik` (`korisnikID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prevoznikID` FOREIGN KEY (`prevoznikID`) REFERENCES `prevoznik` (`prevoznikID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komentar`
--

LOCK TABLES `komentar` WRITE;
/*!40000 ALTER TABLE `komentar` DISABLE KEYS */;
INSERT INTO `komentar` VALUES (1,3,7,'Dobar prevoznik, preporucujem'),(2,4,5,'Autobusi su grozni'),(3,4,4,'Odlican prevoznik'),(4,2,3,'ahdjksahdjkashkdj'),(5,2,3,'hjhjhj');
/*!40000 ALTER TABLE `komentar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnik` (
  `korisnikID` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ulogaID` int(11) NOT NULL,
  PRIMARY KEY (`korisnikID`),
  KEY `ulogaID_idx` (`ulogaID`),
  CONSTRAINT `ulogaID` FOREIGN KEY (`ulogaID`) REFERENCES `ulogakorisnka` (`ulogaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Ognjen','Kulic','admin','admin',1),(2,'Dijana','Zivkovic','radnik','radnik',2),(3,'Ana','Bozinovic','ana','ana',3),(4,'Sofija','Zlatkovic','sofija','sofija',3);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prevoznik`
--

DROP TABLE IF EXISTS `prevoznik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prevoznik` (
  `prevoznikID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `adresa` varchar(45) NOT NULL,
  `grad` varchar(45) NOT NULL,
  `telefon` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prevoznikID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prevoznik`
--

LOCK TABLES `prevoznik` WRITE;
/*!40000 ALTER TABLE `prevoznik` DISABLE KEYS */;
INSERT INTO `prevoznik` VALUES (2,'STUP VRSAC','Stepe Stepanovica 9','Vrsac','031 833 450','saobracaj@stup.rs'),(3,'BANAT TRANS','Beogradska 22','Zrenjanin','023 533 991','info@netbus.rs'),(4,'SIRMIUMBUS','Fruskogorska bb','Sremska Mitrovica','022 621 143','info@sirmiumbus.rs'),(5,'BEST KOMERC BUS','Dimitrija Tucovica 12','Kraljevo','0641165263',NULL),(6,'SEVERTRANS',' Filipa Kljajica bb','Sombor','025 424 425','office@severtrans.rs'),(7,'ŠIDEXPRES',' Janka Veselinovica 19','Šid','022 2710 320','sidexpres@ptt.rs');
/*!40000 ALTER TABLE `prevoznik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ruta` (
  `rutaID` int(11) NOT NULL AUTO_INCREMENT,
  `brMesta` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `prevoznik` int(11) NOT NULL,
  `tipPolaska` int(11) NOT NULL,
  PRIMARY KEY (`rutaID`),
  KEY `prevoznikID_idx` (`prevoznik`),
  KEY `tipPolaska_idx` (`tipPolaska`),
  CONSTRAINT `prevoznik` FOREIGN KEY (`prevoznik`) REFERENCES `prevoznik` (`prevoznikID`) ON UPDATE CASCADE,
  CONSTRAINT `tipPolaska` FOREIGN KEY (`tipPolaska`) REFERENCES `tippolaska` (`tipPolaskaID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (1,85,'2019-04-04',5,1),(2,85,'2019-04-04',5,1),(3,65,'2019-04-04',4,1),(4,30,'2019-04-10',6,1),(5,2,NULL,2,3),(6,3,'2019-05-29',3,3),(7,2,'2019-05-15',2,3),(8,5,'2019-05-18',6,1);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stanica`
--

DROP TABLE IF EXISTS `stanica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stanica` (
  `stanicaID` int(11) NOT NULL AUTO_INCREMENT,
  `cena` int(11) NOT NULL,
  `polazak` datetime DEFAULT NULL,
  `dolazak` datetime DEFAULT NULL,
  `brStanice` int(11) NOT NULL,
  `destinacijaID` int(11) NOT NULL,
  `rutaID` int(11) NOT NULL,
  `brSlobodnihMesta` int(11) NOT NULL,
  PRIMARY KEY (`stanicaID`),
  KEY `destinacijaID_idx` (`destinacijaID`),
  KEY `rutaID_idx` (`rutaID`),
  CONSTRAINT `destinacijaID` FOREIGN KEY (`destinacijaID`) REFERENCES `destinacija` (`destinacijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rutaID` FOREIGN KEY (`rutaID`) REFERENCES `ruta` (`rutaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stanica`
--

LOCK TABLES `stanica` WRITE;
/*!40000 ALTER TABLE `stanica` DISABLE KEYS */;
INSERT INTO `stanica` VALUES (1,100,'2019-04-10 00:00:00',NULL,0,1,4,28),(2,200,'2019-04-10 00:00:00','2019-04-10 00:00:00',1,2,4,30),(3,300,NULL,'2019-04-10 00:00:00',2,3,4,30),(4,100,'2019-05-06 00:00:00',NULL,0,1,5,2),(5,200,NULL,'2019-05-06 00:00:00',1,2,5,2),(6,100,'2019-05-06 00:00:00',NULL,0,1,6,3),(7,200,'2019-05-06 00:00:00','2019-05-06 00:00:00',1,2,6,3),(8,300,NULL,'2019-05-06 00:00:00',2,3,6,3),(9,100,'2019-05-06 00:00:00',NULL,0,5,7,2),(10,200,NULL,'2019-05-06 00:00:00',1,4,7,2),(11,100,'2019-05-18 12:00:59',NULL,0,1,8,4),(12,200,'2019-05-18 12:15:59','2019-05-18 12:10:59',1,2,8,5),(13,300,NULL,'2019-05-18 12:30:59',2,3,8,5);
/*!40000 ALTER TABLE `stanica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tippolaska`
--

DROP TABLE IF EXISTS `tippolaska`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tippolaska` (
  `tipPolaskaID` int(11) NOT NULL AUTO_INCREMENT,
  `nazivTipa` varchar(45) NOT NULL,
  PRIMARY KEY (`tipPolaskaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tippolaska`
--

LOCK TABLES `tippolaska` WRITE;
/*!40000 ALTER TABLE `tippolaska` DISABLE KEYS */;
INSERT INTO `tippolaska` VALUES (1,'Svakodnevni'),(2,'Radnim danima'),(3,'Sezonski'),(4,'Jednokratni');
/*!40000 ALTER TABLE `tippolaska` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ulogakorisnka`
--

DROP TABLE IF EXISTS `ulogakorisnka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ulogakorisnka` (
  `ulogaID` int(11) NOT NULL AUTO_INCREMENT,
  `nazivUloge` varchar(45) NOT NULL,
  PRIMARY KEY (`ulogaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ulogakorisnka`
--

LOCK TABLES `ulogakorisnka` WRITE;
/*!40000 ALTER TABLE `ulogakorisnka` DISABLE KEYS */;
INSERT INTO `ulogakorisnka` VALUES (1,'ADMIN'),(2,'RADNIK'),(3,'PUTNIK');
/*!40000 ALTER TABLE `ulogakorisnka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vrstakarte`
--

DROP TABLE IF EXISTS `vrstakarte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vrstakarte` (
  `vrstaKarteID` int(11) NOT NULL AUTO_INCREMENT,
  `nazivVrste` varchar(45) NOT NULL,
  `popust` double DEFAULT NULL,
  PRIMARY KEY (`vrstaKarteID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vrstakarte`
--

LOCK TABLES `vrstakarte` WRITE;
/*!40000 ALTER TABLE `vrstakarte` DISABLE KEYS */;
INSERT INTO `vrstakarte` VALUES (1,'studentska',0.8),(2,'penzionerska',0.8),(3,'povratna',0.8),(4,'regularna',1);
/*!40000 ALTER TABLE `vrstakarte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-18 13:56:00
