

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS locket;
create database locket;
use locket;

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `almacen` (
  `idAlmacen` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(256) NOT NULL,
  `usuario` varchar(256) NOT NULL,
  `pass` varchar(1024) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `expira` varchar(256) DEFAULT NULL,
  `idKey` varchar(1024) NOT NULL,
  PRIMARY KEY (`idAlmacen`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `idKey` int(11) NOT NULL AUTO_INCREMENT,
  `keyss` varchar(1024) NOT NULL,
  PRIMARY KEY (`idKey`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_almacen`(
Titulo VARCHAR(256),
Usuario VARCHAR(256),
Pass VARCHAR(1024),
Url VARCHAR(1024),
Expira VARCHAR(256) ,
IdKey VARCHAR(1024) )
BEGIN 
insert into almacen (titulo,usuario,pass,url,expira,idKey)
values(Titulo,Usuario,Pass,Url,Expira,IdKey);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_log`(
 llave varchar(1024))
BEGIN 
insert into log(keyss)
values(llave);

END$$

DELIMITER ;


