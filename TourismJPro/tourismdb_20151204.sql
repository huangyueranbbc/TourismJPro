/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.25a : Database - tourismdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tourismdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tourismdb`;

/*Table structure for table `t_bill` */

DROP TABLE IF EXISTS `t_bill`;

CREATE TABLE `t_bill` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `NO` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `recv_account` varchar(100) NOT NULL,
  `bank` varchar(100) NOT NULL,
  `pay_account` float(8,2) NOT NULL,
  `currency` int(11) NOT NULL,
  `money` float(8,2) NOT NULL,
  `paymethod` varchar(100) NOT NULL,
  `type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `remarks` text,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_user_bill` (`user_id`),
  KEY `FK_order_bill` (`order_id`),
  CONSTRAINT `FK_order_bill` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `FK_user_bill` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bill` */

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toursim_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `satisfaction` int(11) NOT NULL,
  `content` text NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_user_comment` (`user_id`),
  KEY `FK_tourism_comment` (`toursim_id`),
  CONSTRAINT `FK_tourism_comment` FOREIGN KEY (`toursim_id`) REFERENCES `t_tourism` (`id`),
  CONSTRAINT `FK_user_comment` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tourism_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `orderId` varchar(100) NOT NULL,
  `price` float(8,2) NOT NULL,
  `tourismTime` datetime NOT NULL,
  `number` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `amount` float(8,2) NOT NULL,
  `remark` text,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_user_order` (`tourism_id`),
  KEY `FK_order_tourism` (`user_id`),
  CONSTRAINT `FK_order_tourism` FOREIGN KEY (`user_id`) REFERENCES `t_tourism` (`id`),
  CONSTRAINT `FK_user_order` FOREIGN KEY (`tourism_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

/*Table structure for table `t_orderprocess` */

DROP TABLE IF EXISTS `t_orderprocess`;

CREATE TABLE `t_orderprocess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `dateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_order_orderProcess` (`order_id`),
  KEY `FK_Reference_10` (`user_id`),
  CONSTRAINT `FK_order_orderProcess` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_orderprocess` */

/*Table structure for table `t_tourism` */

DROP TABLE IF EXISTS `t_tourism`;

CREATE TABLE `t_tourism` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `type` int(11) NOT NULL,
  `city` varchar(100) NOT NULL,
  `days` int(11) NOT NULL,
  `basePrice` int(11) NOT NULL,
  `minNumber` int(11) NOT NULL,
  `maxNumber` int(11) NOT NULL,
  `description` text NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `t_tourism` */

insert  into `t_tourism`(`id`,`title`,`type`,`city`,`days`,`basePrice`,`minNumber`,`maxNumber`,`description`,`del`) values (21,'软库网旅游1',0,'深圳',80,2,1,888,'好玩啦',0),(22,'软库网旅游2',1,'深圳',80,2,1,888,'k可以玩',0),(23,'软库网旅游3',1,'深圳',80,2,1,888,'软库网',0),(24,'软库网旅游4',1,'深圳',80,2,1,888,'可以的',0),(25,'软库网旅游5',1,'深圳',80,2,1,888,'aa',0),(26,'软库网旅游6',1,'深圳',80,2,1,888,'d',0),(27,'软库网旅游7',1,'深圳',80,2,1,888,'a w',0),(28,'软库网旅游8',1,'深圳',80,2,1,888,'d',0),(29,'软库网旅游9',1,'深圳',80,2,1,888,'fsdf',0),(30,'软库网旅游10',0,'深圳',80,2,1,888,'好玩啦',0),(31,'软库网旅游11',0,'深圳',80,2,1,888,'好玩啦',0),(32,'软库网旅游12',1,'深圳',80,2,1,888,'aa',0),(33,'软库网旅游13',1,'深圳',80,2,1,888,'aa',0),(34,'软库网旅游14',1,'深圳',80,2,1,888,'可以的',0),(35,'软库网旅游15',1,'深圳',80,2,1,888,'可以的',0),(36,'软库网旅游16',1,'深圳',80,2,1,888,'软库网',0),(37,'软库网旅游18',1,'深圳',80,2,1,888,'可以的',0),(38,'软库网旅游19',1,'深圳',80,2,1,888,'可以的',0),(39,'软库网旅游20',1,'深圳',80,34,1,888,'软库网',0),(40,'软库网旅游22',1,'深圳',80,34645345,1,888,'可以的',0),(41,'软库网旅游23',1,'深圳',80,45,1,888,'软库网',0),(42,'软库网旅游25',1,'深圳',80,463,1,888,'k可以玩',0),(43,'软库网旅游26',1,'深圳',80,345,1,888,'软库网',0),(44,'软库网旅游27',1,'深圳',80,345,1,888,'软库网',0),(45,'软库网旅游28',1,'深圳',80,6,1,888,'软库网',0),(46,'软库网旅游29',1,'深圳',80,634,1,888,'软库网',0),(47,'软库网旅游30',1,'深圳',80,4234,1,888,'软库网',0),(48,'软库网旅游24',1,'深圳',80,235,1,888,'软库网',0),(49,'软库网旅游21',1,'深圳',80,6346,1,888,'软库网',0),(50,'软库网旅游17',1,'深圳',80,234,1,888,'软库网',0),(51,'软库网旅游',1,'深圳',80,534,1,888,'fewfwe ',0);

/*Table structure for table `t_tourismimages` */

DROP TABLE IF EXISTS `t_tourismimages`;

CREATE TABLE `t_tourismimages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `tourism_id` int(11) NOT NULL,
  `big` varchar(200) NOT NULL,
  `small` varchar(200) NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify` datetime NOT NULL,
  `orders` int(11) NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_image_tourism` (`tourism_id`),
  CONSTRAINT `FK_image_tourism` FOREIGN KEY (`tourism_id`) REFERENCES `t_tourism` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_tourismimages` */

insert  into `t_tourismimages`(`ID`,`tourism_id`,`big`,`small`,`upload_time`,`last_modify`,`orders`,`del`) values (8,21,'upload/001.jpg','upload/001.jpg','2015-12-04 16:43:49','2015-12-04 00:00:00',1814650725,0),(9,21,'upload/002.jpg','upload/002.jpg','2015-12-04 16:43:49','2015-12-04 00:00:00',1814650860,0),(10,22,'upload/002.jpg','upload/002.jpg','2015-12-04 16:44:23','2015-12-04 00:00:00',1814685214,0),(11,22,'upload/004.jpg','upload/004.jpg','2015-12-04 16:44:24','2015-12-04 00:00:00',1814685372,0),(12,23,'upload/004.jpg','upload/004.jpg','2015-12-04 16:44:51','2015-12-04 00:00:00',1814712824,0),(13,24,'upload/001.jpg','upload/001.jpg','2015-12-04 16:47:59','2015-12-04 00:00:00',1814900759,0),(14,25,'upload/002.jpg','upload/002.jpg','2015-12-04 16:49:19','2015-12-04 00:00:00',1814980490,0),(15,26,'upload/004.jpg','upload/004.jpg','2015-12-04 16:49:40','2015-12-04 00:00:00',1815001540,0),(16,27,'upload/002.jpg','upload/002.jpg','2015-12-04 16:49:59','2015-12-04 00:00:00',1815020840,0),(17,28,'upload/004.jpg','upload/004.jpg','2015-12-04 16:50:17','2015-12-04 00:00:00',1815038291,0),(18,29,'upload/004.jpg','upload/004.jpg','2015-12-04 16:50:34','2015-12-04 00:00:00',1815055410,0),(19,51,'upload/002.jpg','upload/002.jpg','2015-12-04 16:54:16','2015-12-04 00:00:00',1815278120,0);

/*Table structure for table `t_tourismrule` */

DROP TABLE IF EXISTS `t_tourismrule`;

CREATE TABLE `t_tourismrule` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `tourism_id` int(11) NOT NULL,
  `deadline` datetime NOT NULL,
  `tourismTime` datetime NOT NULL,
  `price` float(8,2) NOT NULL,
  `discount` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  `remark` text,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_tourism_tourismRule` (`tourism_id`),
  CONSTRAINT `FK_tourism_tourismRule` FOREIGN KEY (`tourism_id`) REFERENCES `t_tourism` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `t_tourismrule` */

insert  into `t_tourismrule`(`ID`,`tourism_id`,`deadline`,`tourismTime`,`price`,`discount`,`status`,`remark`,`modifyTime`,`del`) values (22,21,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:43:38',0),(23,22,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:44:12',0),(24,23,'2016-05-10 00:00:00','2016-05-13 00:00:00',25.00,'666',0,'好玩','2015-12-04 16:44:44',0),(25,24,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:47:52',0),(26,25,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:49:15',0),(27,26,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:49:34',0),(28,27,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:49:55',0),(29,28,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:50:13',0),(30,29,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:50:30',0),(31,51,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-04 16:54:08',0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`,`password`,`phone`,`email`,`type`,`status`,`del`) values (1,'admin','admin','11','admin@qq.com',1,0,0),(2,'a','a','11','a',0,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
