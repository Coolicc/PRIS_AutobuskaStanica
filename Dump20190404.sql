-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: autobuska_stanica
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `destinacija`
--

DROP TABLE IF EXISTS `destinacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `karta` (
  `kartaID` int(11) NOT NULL AUTO_INCREMENT,
  `konacnaCena` int(11) NOT NULL,
  `vrstaKarte` int(11) NOT NULL,
  `datumRezervacije` date DEFAULT NULL,
  `datumProdaje` date DEFAULT NULL,
  `rezervisao` int(11) DEFAULT NULL,
  `prodao` int(11) DEFAULT NULL,
  `ruta` int(11) NOT NULL,
  PRIMARY KEY (`kartaID`),
  KEY `vrstaKarte_idx` (`vrstaKarte`),
  KEY `rezervisao_idx` (`rezervisao`),
  KEY `prodao_idx` (`prodao`),
  KEY `ruta_idx` (`ruta`),
  CONSTRAINT `prodao` FOREIGN KEY (`prodao`) REFERENCES `korisnik` (`korisnikID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rezervisao` FOREIGN KEY (`rezervisao`) REFERENCES `korisnik` (`korisnikID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ruta` FOREIGN KEY (`ruta`) REFERENCES `ruta` (`rutaID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vrstaKarte` FOREIGN KEY (`vrstaKarte`) REFERENCES `vrstakarte` (`vrstaKarteID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karta`
--

LOCK TABLES `karta` WRITE;
/*!40000 ALTER TABLE `karta` DISABLE KEYS */;
INSERT INTO `karta` VALUES (1,1184,1,'2019-04-04',NULL,4,NULL,1),(2,368,1,'2019-04-04','2019-04-04',3,2,3);
/*!40000 ALTER TABLE `karta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komentar`
--

LOCK TABLES `komentar` WRITE;
/*!40000 ALTER TABLE `komentar` DISABLE KEYS */;
INSERT INTO `komentar` VALUES (1,3,7,'Dobar prevoznik, preporucujem'),(2,4,5,'Autobusi su grozni'),(3,4,4,'Odlican prevoznik');
/*!40000 ALTER TABLE `komentar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnik` (
  `korisnikID` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ulogaID` int(11) NOT NULL,
  PRIMARY KEY (`korisnikID`),
  KEY `ulogaID_idx` (`ulogaID`),
  CONSTRAINT `ulogaID` FOREIGN KEY (`ulogaID`) REFERENCES `ulogakorisnka` (`ulogaID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruta` (
  `rutaID` int(11) NOT NULL AUTO_INCREMENT,
  `brMesta` int(11) NOT NULL,
  `cena` int(11) NOT NULL,
  `datum` date NOT NULL,
  `prevoznik` int(11) NOT NULL,
  `tipPolaska` int(11) NOT NULL,
  `polazak` int(11) NOT NULL,
  `dolazak` int(11) NOT NULL,
  PRIMARY KEY (`rutaID`),
  KEY `prevoznikID_idx` (`prevoznik`),
  KEY `tipPolaska_idx` (`tipPolaska`),
  KEY `polazak_idx` (`polazak`),
  KEY `dolazak_idx` (`dolazak`),
  CONSTRAINT `dolazak` FOREIGN KEY (`dolazak`) REFERENCES `destinacija` (`destinacijaID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `polazak` FOREIGN KEY (`polazak`) REFERENCES `destinacija` (`destinacijaID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `prevoznik` FOREIGN KEY (`prevoznik`) REFERENCES `prevoznik` (`prevoznikID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `tipPolaska` FOREIGN KEY (`tipPolaska`) REFERENCES `tippolaska` (`tipPolaskaID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (1,85,1480,'2019-04-04',5,1,5,3),(2,85,1480,'2019-04-04',5,1,5,2),(3,65,460,'2019-04-04',4,1,5,1);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tippolaska`
--

DROP TABLE IF EXISTS `tippolaska`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
INSERT INTO `vrstakarte` VALUES (1,'studentska',0.8),(2,'penzionerska',0.8),(3,'povratna',0.8),(4,'regularna',NULL);
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

-- Dump completed on 2019-04-04 20:24:48
