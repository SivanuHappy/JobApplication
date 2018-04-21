-- CREATE DATABASE  IF NOT EXISTS `jobapp`;--
USE `jobapp`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

-- Create user table
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` char(68) NOT NULL,
  `confirmpassword` char(68) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `applicant`;
-- Create applicant table
CREATE TABLE `applicant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `address1` varchar(45) DEFAULT NULL,
  `address2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` int(10) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `institution` varchar(45) DEFAULT NULL,
  `degree` varchar(45) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `skills` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`userid`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employer`;
-- Create employer table
CREATE TABLE `employer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EMPLOYER_idx` (`userid`),
  CONSTRAINT `FK_EMPLOYER` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `job`;

-- Create user table
CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employerid` int(11) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `skills` varchar(45) DEFAULT NULL,
  `salary` int(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_JOB_idx` (`employerid`),
  CONSTRAINT `FK_JOB` FOREIGN KEY (`employerid`) REFERENCES `employer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

drop table `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` char(68) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO admin VALUES(1, 'admin', 'Admin', 'Admin', 'admin');


