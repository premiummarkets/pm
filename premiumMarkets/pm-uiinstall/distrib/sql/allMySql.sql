-- MySQL dump 10.13  Distrib 5.7.22, for Linux (i686)
--
-- Host: localhost    Database: premiummarkets
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `ALERTONEVENT`
--

DROP TABLE IF EXISTS `ALERTONEVENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALERTONEVENT` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `EVENTINFOREFERENCE` varchar(100) NOT NULL,
  `MONITORLEVEL` smallint(6) NOT NULL DEFAULT '0',
  `OPTIONALMESSAGE` mediumtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ALERTS`
--

DROP TABLE IF EXISTS `ALERTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALERTS` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `ALERTTYPE` varchar(255) NOT NULL,
  `THRESHOLDTYPE` varchar(255) NOT NULL,
  `VALUE` decimal(20,4) NOT NULL,
  `OPTIONALMESSAGE` mediumtext,
  PRIMARY KEY (`SYMBOL`,`ISIN`,`NAME`,`THRESHOLDTYPE`,`ALERTTYPE`),
  CONSTRAINT `FK_ALERTS_TO_PORTFOLIO` FOREIGN KEY (`SYMBOL`, `ISIN`, `NAME`) REFERENCES `PORTFOLIO` (`SYMBOL`, `ISIN`, `NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CURRENCYRATE`
--

DROP TABLE IF EXISTS `CURRENCYRATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CURRENCYRATE` (
  `FROMCURRENCY` int(11) NOT NULL,
  `TOCURRENCY` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `RATE` decimal(20,8) DEFAULT '1.00000000',
  PRIMARY KEY (`FROMCURRENCY`,`TOCURRENCY`,`DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EVENTS`
--

DROP TABLE IF EXISTS `EVENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EVENTS` (
  `DATE` datetime NOT NULL,
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `EVENTDEF` varchar(100) NOT NULL,
  `EVENTTYPE` varchar(1) NOT NULL,
  `ACCURACY` smallint(6) DEFAULT NULL,
  `EVENTDEFID` smallint(6) NOT NULL,
  `EVENTDEFEXTENSION` varchar(100) NOT NULL DEFAULT '',
  `ANALYSENAME` varchar(256) NOT NULL DEFAULT 'AutoPortfolio',
  `MESSAGE` mediumtext,
  PRIMARY KEY (`ANALYSENAME`,`SYMBOL`,`ISIN`,`DATE`,`EVENTDEF`,`EVENTDEFEXTENSION`),
  KEY `EVENTS_ANAME` (`ANALYSENAME`),
  KEY `EVENTS_ANAME_DATE` (`ANALYSENAME`,`DATE`),
  KEY `EVENTS_STOCK_ANAME_DATE_DEF` (`ANALYSENAME`,`SYMBOL`,`ISIN`,`DATE`,`EVENTDEF`),
  KEY `EVENTS_STOCK_ANAME_DATE_DEF_TYPE` (`ANALYSENAME`,`SYMBOL`,`ISIN`,`DATE`,`EVENTDEF`,`EVENTTYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EVENTSCACHE`
--

DROP TABLE IF EXISTS `EVENTSCACHE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EVENTSCACHE` (
  `DATE` datetime NOT NULL,
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `EVENTDEF` varchar(100) NOT NULL,
  `EVENTTYPE` varchar(1) NOT NULL,
  `ACCURACY` smallint(6) DEFAULT NULL,
  `EVENTDEFID` smallint(6) NOT NULL,
  `EVENTDEFEXTENSION` varchar(100) NOT NULL DEFAULT '',
  `ANALYSENAME` varchar(256) NOT NULL,
  `MESSAGE` mediumtext,
  PRIMARY KEY (`SYMBOL`,`ISIN`,`ANALYSENAME`,`DATE`,`EVENTDEF`,`EVENTDEFEXTENSION`),
  KEY `EVENTS_ANAME_DATE` (`ANALYSENAME`,`DATE`),
  KEY `EVENTS_STOCK_ANAME_DATE_DEF` (`SYMBOL`,`ISIN`,`ANALYSENAME`,`DATE`,`EVENTDEF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PERF_SUPPLEMENT`
--

DROP TABLE IF EXISTS `PERF_SUPPLEMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERF_SUPPLEMENT` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PERFDATE` date NOT NULL,
  `LATEST` smallint(6) NOT NULL DEFAULT '1',
  `TRENDFORECAST` varchar(32) DEFAULT NULL,
  `ISREVERSAL` smallint(6) NOT NULL DEFAULT '0',
  `YEARLYPERF` decimal(10,4) DEFAULT NULL,
  `MONTHLYPERF` decimal(10,4) DEFAULT NULL,
  `WEEKLYPERF` decimal(10,4) DEFAULT NULL,
  `DAILYPERF` decimal(10,4) DEFAULT NULL,
  `EVENTDEFINITION` varchar(100) NOT NULL,
  PRIMARY KEY (`SYMBOL`,`ISIN`,`NAME`,`PERFDATE`,`EVENTDEFINITION`),
  KEY `FK_PERF_SUPPLEMENT_TO_PORTFOLIO` (`SYMBOL`,`ISIN`,`NAME`),
  KEY `PERF_SUPPLEMENT_LATEST` (`LATEST`),
  KEY `PERF_SUPPLEMENT_KEY` (`SYMBOL`,`ISIN`,`NAME`,`EVENTDEFINITION`),
  CONSTRAINT `FK_PERF_SUPPLEMENT_TO_PORTFOLIO` FOREIGN KEY (`SYMBOL`, `ISIN`, `NAME`) REFERENCES `PORTFOLIO` (`SYMBOL`, `ISIN`, `NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PORTFOLIO`
--

DROP TABLE IF EXISTS `PORTFOLIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PORTFOLIO` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `QUANTITY` decimal(19,5) DEFAULT NULL,
  `MONITOR` smallint(6) NOT NULL DEFAULT '0',
  `BUYDATE` date DEFAULT '1970-01-01',
  `TRANSACTIONCURRENCY` char(3) NOT NULL DEFAULT 'EUR',
  `EXTERNALACCOUNT` varchar(255) DEFAULT NULL,
  `AVGBUYPRICE` decimal(20,4) DEFAULT '0.0000',
  `CASHIN` decimal(20,4) DEFAULT '0.0000',
  `CASHOUT` decimal(20,4) DEFAULT '0.0000',
  PRIMARY KEY (`SYMBOL`,`ISIN`,`NAME`),
  KEY `FK_PORTFOLIO_TO_PORTFOLIO_NAME` (`NAME`),
  KEY `FK_STOCK` (`SYMBOL`,`ISIN`),
  CONSTRAINT `FK_PORTFOLIO_TO_PORTFOLIO_NAME` FOREIGN KEY (`NAME`) REFERENCES `PORTFOLIO_NAME` (`NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PORTFOLIO_TO_SHARES` FOREIGN KEY (`SYMBOL`, `ISIN`) REFERENCES `SHARES` (`SYMBOL`, `ISIN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PORTFOLIO_NAME`
--

DROP TABLE IF EXISTS `PORTFOLIO_NAME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PORTFOLIO_NAME` (
  `NAME` varchar(255) NOT NULL,
  `TYPE` varchar(32) DEFAULT 'default',
  `BUYPONDERATIONRULE` longblob,
  `SELLPONDERATIONRULE` longblob,
  `PORTFOLIOCURRENCY` char(3) DEFAULT NULL,
  `TOTALINAMOUNTEVER` decimal(20,4) DEFAULT '0.0000',
  `TOTALOUTAMOUNTEVER` decimal(20,4) DEFAULT '0.0000',
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `QUOTATIONS`
--

DROP TABLE IF EXISTS `QUOTATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUOTATIONS` (
  `DATE` date NOT NULL,
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `CURRENCY` char(3) DEFAULT NULL,
  `VOLUME` bigint(20) DEFAULT '0',
  `OPENVALUE` decimal(20,4) DEFAULT NULL,
  `CLOSEVALUE` decimal(20,4) DEFAULT NULL,
  `HIGH` decimal(20,4) DEFAULT NULL,
  `LOW` decimal(20,4) DEFAULT NULL,
  `ORIGIN` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`SYMBOL`,`ISIN`,`DATE`,`ORIGIN`),
  KEY `SYMBOL_ISIN_INDEX` (`SYMBOL`,`ISIN`),
  KEY `STOCK_DATE_ORIGIN` (`SYMBOL`,`ISIN`,`DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SHARES`
--

DROP TABLE IF EXISTS `SHARES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SHARES` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `LASTQUOTE` date NOT NULL,
  `REMOVABLE` smallint(6) NOT NULL DEFAULT '0',
  `QUOTATIONPROVIDER` varchar(32) DEFAULT NULL,
  `MARKETLISTPROVIDER` varchar(32) DEFAULT NULL,
  `CATEGORY` varchar(255) DEFAULT NULL,
  `SECTOR_HINT` varchar(64) DEFAULT NULL,
  `TRADING_MODE` varchar(64) NOT NULL DEFAULT 'UNKNOWN',
  `CAPITALISATION` bigint(20) NOT NULL DEFAULT '0',
  `CURRENCYFACTOR` decimal(9,2) NOT NULL DEFAULT '1.00',
  `CURRENCY` varchar(32) NOT NULL,
  PRIMARY KEY (`SYMBOL`,`ISIN`),
  KEY `SHARES_SYMBOL` (`SYMBOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TRANSACTIONS`
--

DROP TABLE IF EXISTS `TRANSACTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSACTIONS` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `EXTERNALACCOUNT` varchar(255) DEFAULT NULL,
  `DATE` date NOT NULL,
  `QUANTITY` decimal(19,5) DEFAULT NULL,
  `PRICE` decimal(20,4) DEFAULT NULL,
  `CURRENCY` char(3) NOT NULL DEFAULT 'EUR',
  `PORTFOLIO` varchar(255) DEFAULT NULL,
  `ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TREND_SUPPLEMENT`
--

DROP TABLE IF EXISTS `TREND_SUPPLEMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TREND_SUPPLEMENT` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `BOURSOMEANRECOMMENDATIONS` decimal(19,2) DEFAULT NULL,
  `BOURSOTARGETPRICE` decimal(19,2) DEFAULT NULL,
  `YAHOOMEANRECOMMENDATIONS` decimal(19,2) DEFAULT NULL,
  `YAHOOTARGETPRICE` decimal(19,2) DEFAULT NULL,
  `DIVIDEND` decimal(10,2) DEFAULT NULL,
  `REUTERSYIELD` decimal(10,2) DEFAULT NULL,
  `YAHOOEPS` decimal(19,2) DEFAULT NULL,
  `YAHOOESTEPS` decimal(19,2) DEFAULT NULL,
  `REUTERSEPS` decimal(19,2) DEFAULT NULL,
  `REUTERSESTEPS` decimal(19,2) DEFAULT NULL,
  `REUTERSPAYOUTRATIO` decimal(19,2) DEFAULT NULL,
  `BOURSOBNA` decimal(19,2) DEFAULT NULL,
  `BOURSOESTBNA` decimal(19,2) DEFAULT NULL,
  `TRENDDATE` date NOT NULL DEFAULT '1970-01-01',
  UNIQUE KEY `TREND_SUPPLEMENT_PKEY` (`TRENDDATE`,`SYMBOL`,`ISIN`),
  KEY `FK_TREND_SUPPLEMENT_TO_SHARES` (`SYMBOL`,`ISIN`),
  CONSTRAINT `FK_TREND_SUPPLEMENT_TO_SHARES` FOREIGN KEY (`SYMBOL`, `ISIN`) REFERENCES `SHARES` (`SYMBOL`, `ISIN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TUNEDCONF`
--

DROP TABLE IF EXISTS `TUNEDCONF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TUNEDCONF` (
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `CONFIGFILE` varchar(50) CHARACTER SET latin1 NOT NULL,
  `LASTCALCULATIONSTART` date NOT NULL DEFAULT '1970-01-01',
  `LASTCALCULATEDEVENT` date DEFAULT NULL,
  `LASTCALCULATIONEND` date NOT NULL DEFAULT '1970-01-01',
  `EVENTDEFINITION` varchar(100) NOT NULL,
  `DIRTY` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`SYMBOL`,`ISIN`,`CONFIGFILE`,`EVENTDEFINITION`),
  CONSTRAINT `FK_TUNEDCONF_TO_SHARES` FOREIGN KEY (`SYMBOL`, `ISIN`) REFERENCES `SHARES` (`SYMBOL`, `ISIN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `EMAIL` varchar(100) NOT NULL,
  `SYMBOL` varchar(20) NOT NULL,
  `ISIN` varchar(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `COMMENT` mediumtext,
  `REGISTRATIONDATE` date NOT NULL,
  `REGISTRATIONFORECAST` varchar(32) DEFAULT NULL,
  `ISACTIVE` smallint(6) NOT NULL DEFAULT '1',
  `EVENTDEFINITION` varchar(100) NOT NULL DEFAULT '',
  `STOCKPARAMS` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`EMAIL`,`SYMBOL`,`ISIN`,`NAME`,`EVENTDEFINITION`,`STOCKPARAMS`),
  KEY `FK_USER_TO_PORTFOLIO` (`SYMBOL`,`ISIN`,`NAME`),
  CONSTRAINT `FK_USER_TO_PORTFOLIO` FOREIGN KEY (`SYMBOL`, `ISIN`, `NAME`) REFERENCES `PORTFOLIO` (`SYMBOL`, `ISIN`, `NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USERS_CP`
--

DROP TABLE IF EXISTS `USERS_CP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS_CP` (
  `EMAIL` varchar(100) CHARACTER SET utf8 NOT NULL,
  `SYMBOL` varchar(20) CHARACTER SET utf8 NOT NULL,
  `ISIN` varchar(20) CHARACTER SET utf8 NOT NULL,
  `NAME` varchar(255) CHARACTER SET utf8 NOT NULL,
  `COMMENT` mediumtext CHARACTER SET utf8,
  `REGISTRATIONDATE` date NOT NULL,
  `REGISTRATIONFORECAST` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `ISACTIVE` smallint(6) NOT NULL DEFAULT '1',
  `EVENTDEFINITION` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `STOCKPARAMS` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WEATHER`
--

DROP TABLE IF EXISTS `WEATHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WEATHER` (
  `DATE` date NOT NULL,
  `MAXTEMP` smallint(6) DEFAULT NULL,
  `AVGTEMP` smallint(6) DEFAULT NULL,
  `MINTEMP` smallint(6) DEFAULT NULL,
  `PRECIPITATION` decimal(19,5) DEFAULT NULL,
  `WIND` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-28  9:48:05