/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - subas-eshopproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`subas-eshopproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `subas-eshopproject`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `text` text,
  `created_date` date NOT NULL,
  `pic_url` varchar(255) NOT NULL,
  `blog_category_id` int(11) NOT NULL,
  `comment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_id` (`comment_id`),
  KEY `blog_ibfk_1` (`blog_category_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`blog_category_id`) REFERENCES `blog_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

insert  into `blog`(`id`,`name`,`text`,`created_date`,`pic_url`,`blog_category_id`,`comment_id`) values (1,'Home','home-home','2021-02-19','1613760965286_connected-technology.jpg',1,NULL),(2,'E-Commerce','E-Commerce','2021-02-19','1613760981524_onlineShop.jpg',3,NULL),(3,'Education','Education','2021-02-19','1613760997731_techBlog.jpg',2,NULL);

/*Table structure for table `blog_category` */

DROP TABLE IF EXISTS `blog_category`;

CREATE TABLE `blog_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `blog_category` */

insert  into `blog_category`(`id`,`name`) values (1,'Tech'),(2,'Education'),(3,'Finance');

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pic_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`,`pic_url`) values (1,'Samsung','1613760165219_brandPic.jpeg'),(2,'Apple','1613760179105_brandPic.jpg'),(3,'Sony','1613760195695_brandPic.jpg'),(4,'LG','1613760207287_brandPic.png');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values (1,'SmartPhone'),(2,'NoteBook'),(3,'Tablet');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` text NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_ibfk_1` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`comment_text`,`created_date`,`user_id`) values (1,'super','2021-02-19 18:56:56',1);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `operating_system` enum('IOS','WINDOWS_PHONE','SYMBIAN','ANDROID') NOT NULL,
  `price` double NOT NULL,
  `color` enum('BLACK','DEEP_SKY_BLUE','LIGHT_SKY_BLUE','GREY','WHITE') DEFAULT NULL,
  `product_type` enum('NORMAL','FEATURED') NOT NULL DEFAULT 'NORMAL',
  `product_list_type` enum('POPULAR','NEW_ARRIVAL','BEST_SELLER','SPECIAL_OFFER') NOT NULL DEFAULT 'NEW_ARRIVAL',
  `description` text,
  `pic_url` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `brand_id` (`brand_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`brand_id`,`operating_system`,`price`,`color`,`product_type`,`product_list_type`,`description`,`pic_url`,`category_id`) values (1,'Samsung Galaxy S20',1,'ANDROID',1000,'LIGHT_SKY_BLUE','FEATURED','NEW_ARRIVAL','Samsung Galaxy S20','1613760249103_S20.jpg',1),(2,'Samsung Galaxy S10',1,'ANDROID',900,'BLACK','FEATURED','NEW_ARRIVAL','Samsung Galaxy S10','1613760265295_S10.jpg',1),(3,'Samsung Galaxy S9',1,'ANDROID',850,'BLACK','NORMAL','POPULAR','Samsung Galaxy S9','1613760289859_S9.jpg',1),(4,'Samsung Galaxy S8',1,'ANDROID',700,'BLACK','NORMAL','BEST_SELLER','Samsung Galaxy S8','1613760304310_S8.jpg',1),(5,'iPhone 11 Pro',2,'IOS',2000,'GREY','FEATURED','SPECIAL_OFFER','iPhone 11 Pro','1613760347342_iphone11.png',1),(6,'iPhone X',2,'IOS',1500,'WHITE','FEATURED','POPULAR','','1613760375191_i10.jpg',1),(7,'iPhone 8',2,'IOS',1000,'BLACK','NORMAL','POPULAR','iPhone 8','1613760404755_i8.jpg',1),(8,'iPhone 5S',2,'IOS',150,'BLACK','NORMAL','BEST_SELLER','iPhone 5S','1613760441055_i5S.jpg',1),(9,'iPhone 7',2,'ANDROID',1000,'GREY','NORMAL','POPULAR','iPhone 7','1613760469731_i7.jpg',1),(11,'Sony Xperia Z5',3,'ANDROID',500,'GREY','NORMAL','POPULAR','Sony Xperia Z5','1613760549901_z5.jpeg',1),(12,'Sony Xperia',3,'ANDROID',600,'BLACK','NORMAL','NEW_ARRIVAL','Sony Xperia','1613760576829_xperia.jpg',1),(13,'Sony Xperia X1',3,'ANDROID',400,'BLACK','NORMAL','NEW_ARRIVAL','Sony Xperia X1','1613760601122_x1.jpg',1),(14,'Lg G7',4,'ANDROID',350,'GREY','NORMAL','NEW_ARRIVAL','Lg G7','1613760644051_lgG7.jpg',1),(15,'Lg G5',4,'ANDROID',250,'WHITE','NORMAL','NEW_ARRIVAL','Lg G5','1613760671658_g5.jpg',1),(16,'Iphone 12',2,'IOS',2500,'DEEP_SKY_BLUE','FEATURED','NEW_ARRIVAL','Iphone 12','1613760745899_ipone12.png',1);

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`category_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `product_category_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_category` */

/*Table structure for table `team_member` */

DROP TABLE IF EXISTS `team_member`;

CREATE TABLE `team_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `bio` text NOT NULL,
  `pic_url` varchar(255) NOT NULL,
  `member_type` enum('CHAIRMAN','SEO','COORDINATOR','DIRECTOR','CHIEF') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `team_member` */

insert  into `team_member`(`id`,`name`,`surname`,`bio`,`pic_url`,`member_type`) values (1,'Aram','Sukiasyan','qweqweqwe','1613761078807_20180607_081706.jpg','CHAIRMAN'),(2,'Poxos','Poxosyan','adasdas','1613761101003_chief.png','SEO'),(3,'Petros','Petrosyan','dfadasd','1613761118778_chief.png','COORDINATOR'),(4,'Minas','Minasyan','asdasd','1613761139628_chief.png','DIRECTOR');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `additional_info` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`first_name`,`last_name`,`phone`,`email`,`password`,`additional_info`,`token`,`active`,`role`) values (1,'admin','admin','000000','admin','$2a$10$v83qAMKq.QkNmSnSFnDvmu/GNHSIVDS.T/8z9UAIt1hDoxQmPgX26',NULL,'',1,'ADMIN'),(3,'user','user','111111','user','$2a$10$WUFYniiJ3RBoYv943eoM4ecCLsvGNx3ON/koJrMq8UvnrvvE20Coe',NULL,'',1,'USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
