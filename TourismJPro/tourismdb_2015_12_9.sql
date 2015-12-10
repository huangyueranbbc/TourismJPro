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

/*Table structure for table `t_bank` */

DROP TABLE IF EXISTS `t_bank`;

CREATE TABLE `t_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `money` float(8,2) DEFAULT NULL,
  `appid` varchar(100) DEFAULT '1001',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_bank` */

insert  into `t_bank`(`id`,`name`,`password`,`money`,`appid`) values (1,'ruanko','ruanko',281375.00,'1001'),(2,'1','1',568625.00,'1001');

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
  CONSTRAINT `FK_user_bill` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_order_bill` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_bill` */

insert  into `t_bill`(`ID`,`user_id`,`order_id`,`NO`,`user_name`,`recv_account`,`bank`,`pay_account`,`currency`,`money`,`paymethod`,`type`,`status`,`remarks`,`del`) values (1,1,2,'2015-12-0911449648776717','1','ruanko','中国银行',525.00,0,525.00,'在线支付',0,3,'软库网旅游3:在线支付中国银行 支付金额:525.0',0),(2,1,2,'2015-12-0911449648776717','1','ruanko','中国银行',525.00,0,525.00,'在线支付',0,3,'软库网旅游3:在线支付中国银行 支付金额:525.0',0),(3,1,3,'2015-12-0911449648821406','1','ruanko','中国银行',11655.00,0,11655.00,'在线支付',0,3,'软库网旅游:在线支付中国银行 支付金额:11655.0',0),(4,1,4,'2015-12-0911449648830755','1','ruanko','中国银行',555.00,0,555.00,'在线支付',0,3,'软库网旅游3:在线支付中国银行 支付金额:555.0',0),(5,1,5,'2015-12-0911449648840337','1','ruanko','中国银行',218115.00,0,218115.00,'在线支付',0,3,'软库网旅游:在线支付中国银行 支付金额:218115.0',0);

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
  KEY `FK_tourism_comment` (`toursim_id`),
  KEY `FK_user_comment` (`user_id`),
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
  KEY `FK_user_order` (`user_id`),
  KEY `FK_order_tourism` (`tourism_id`),
  CONSTRAINT `FK_user_order` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_order_tourism` FOREIGN KEY (`tourism_id`) REFERENCES `t_tourism` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`tourism_id`,`user_id`,`orderId`,`price`,`tourismTime`,`number`,`status`,`amount`,`remark`,`createTime`,`del`) values (1,5,1,'2015-12-0911449648683411',25.00,'2016-05-13 00:00:00',1,1,25.00,'软库网旅游3','2015-12-09 16:11:23',0),(2,5,1,'2015-12-0911449648776717',25.00,'2016-05-13 00:00:00',21,2,525.00,'软库网旅游3','2015-12-09 16:12:56',0),(3,1,1,'2015-12-0911449648821406',555.00,'2016-05-13 00:00:00',21,2,11655.00,'软库网旅游','2015-12-09 16:13:41',0),(4,4,1,'2015-12-0911449648830755',555.00,'2016-05-13 00:00:00',1,2,555.00,'软库网旅游3','2015-12-09 16:13:50',0),(5,1,1,'2015-12-0911449648840337',555.00,'2016-05-13 00:00:00',393,2,218115.00,'软库网旅游','2015-12-09 16:14:00',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_orderprocess` */

insert  into `t_orderprocess`(`id`,`user_id`,`order_id`,`type`,`dateTime`,`del`) values (1,1,1,1,'2015-12-09 16:11:23',0),(2,1,2,2,'2015-12-09 16:12:56',0),(3,1,3,2,'2015-12-09 16:13:41',0),(4,1,4,2,'2015-12-09 16:13:50',0),(5,1,5,2,'2015-12-09 16:14:00',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_tourism` */

insert  into `t_tourism`(`id`,`title`,`type`,`city`,`days`,`basePrice`,`minNumber`,`maxNumber`,`description`,`del`) values (1,'软库网旅游',1,'深圳',80,2,1,888,'很好完',0),(2,'软库网旅游1',0,'深圳',80,2,1,888,'很好完',0),(3,'软库网旅游2',1,'深圳',80,2,1,888,'很好完',0),(4,'软库网旅游3',1,'深圳',80,2,1,888,'很好完',0),(5,'软库网旅游3',0,'深圳',80,2,1,888,'很好完',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_tourismimages` */

insert  into `t_tourismimages`(`ID`,`tourism_id`,`big`,`small`,`upload_time`,`last_modify`,`orders`,`del`) values (1,1,'upload/001.jpg','upload/001.jpg','2015-12-09 16:08:37','2015-12-09 00:00:00',-2050428494,0),(2,1,'upload/002.jpg','upload/002.jpg','2015-12-09 16:08:37','2015-12-09 00:00:00',-2050428434,0),(3,2,'upload/002.jpg','upload/002.jpg','2015-12-09 16:08:57','2015-12-09 00:00:00',-2050408191,0),(4,2,'upload/003.jpg','upload/003.jpg','2015-12-09 16:08:57','2015-12-09 00:00:00',-2050408139,0),(5,3,'upload/003.jpg','upload/003.jpg','2015-12-09 16:09:19','2015-12-09 00:00:00',-2050386144,0),(6,3,'upload/001.jpg','upload/001.jpg','2015-12-09 16:09:20','2015-12-09 00:00:00',-2050385982,0),(7,4,'upload/003.jpg','upload/003.jpg','2015-12-09 16:09:47','2015-12-09 00:00:00',-2050358870,0),(8,4,'upload/002.jpg','upload/002.jpg','2015-12-09 16:09:47','2015-12-09 00:00:00',-2050358800,0),(9,5,'upload/002.jpg','upload/002.jpg','2015-12-09 16:10:13','2015-12-09 00:00:00',-2050332395,0),(10,5,'upload/004.jpg','upload/004.jpg','2015-12-09 16:10:13','2015-12-09 00:00:00',-2050332325,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_tourismrule` */

insert  into `t_tourismrule`(`ID`,`tourism_id`,`deadline`,`tourismTime`,`price`,`discount`,`status`,`remark`,`modifyTime`,`del`) values (1,1,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-09 16:08:29',0),(2,2,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-09 16:08:50',0),(3,3,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-09 16:09:13',0),(4,4,'2016-05-10 00:00:00','2016-05-13 00:00:00',555.00,'666',0,'好玩','2015-12-09 16:09:38',0),(5,5,'2016-05-10 00:00:00','2016-05-13 00:00:00',25.00,'666',0,'好玩','2015-12-09 16:10:06',0);

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

insert  into `t_user`(`id`,`name`,`password`,`phone`,`email`,`type`,`status`,`del`) values (1,'huangyueran','huangyueran','111','111@qq.com',0,0,0),(2,'admin','admin','222','admin@qq.com',1,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
