/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.37 : Database - design
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`design` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `design`;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` varchar(10) NOT NULL COMMENT '学号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `gender` char(3) DEFAULT NULL COMMENT '性别',
  `politicalStatus` varchar(6) DEFAULT NULL COMMENT '政治面貌',
  `birthday` varchar(20) DEFAULT NULL COMMENT '出生年月',
  `profession` varchar(20) NOT NULL COMMENT '专业',
  `birthplace` varchar(100) DEFAULT NULL COMMENT '籍贯',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`gender`,`politicalStatus`,`birthday`,`profession`,`birthplace`) values ('2014000001','李达康','男','党员','1996-10-12','政法学','京城'),('2014000002','陈长生','男','群众','1995-10-11','中医学','国教院'),('2014000003','孙悟空','男','群众','2017-05-01','计算机网络','花果山'),('2014000004','猪八戒','男','群众','1970-01-01','软件工程','天庭'),('2014000005','唐三藏','男','党员','2017-05-01','信息安全','大唐'),('2014000006','沙悟净','男','团员','2017-01-02','统计学','流沙河'),('2014000007','至尊宝','男','团员','2017-03-15','会计','西天之路'),('2014000009','李星云','男','群众','2017-04-01','中医学',NULL),('2014000010','李茂贞','男','党员','2017-04-01','工商企业管理',NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname_unique` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`) values (2,'Lindy','123456'),(3,'admin1','admin1'),(5,'wangwu','123456'),(7,'Tom','123456'),(8,'Bob','123456'),(9,'Jom','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
