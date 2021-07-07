/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20 : Database - teachmanagesys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`teachmanagesys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `teachmanagesys`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL COMMENT '管理员Id',
  `aName` char(100) DEFAULT NULL COMMENT '管理员名',
  `avatar` char(200) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`aid`,`aName`,`avatar`) values (1000,'杨培豪','https://sshop-1302763867.cos.ap-nanjing.myqcloud.com/proFile/1000/3.jpg');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cId` int(10) NOT NULL AUTO_INCREMENT,
  `cName` char(100) NOT NULL,
  `cTime` int(1) DEFAULT NULL,
  `cPlace` char(100) DEFAULT NULL,
  `cWeek` int(3) DEFAULT NULL,
  `cType` int(11) DEFAULT NULL COMMENT '课程的类型，0表示必修课，1表示选修课',
  `cValue` int(11) DEFAULT NULL COMMENT '表示学分',
  `dId` int(3) DEFAULT NULL COMMENT '表示该课程所属于的院系',
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=4008 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`cId`,`cName`,`cTime`,`cPlace`,`cWeek`,`cType`,`cValue`,`dId`) values (1,'C语言程序设计',2,'21B228中教室',18,0,3,NULL),(10,'Python爬虫',4,'N402',18,0,3,NULL),(11,'数据结构',4,'科学楼401',18,0,4,NULL),(12,'Java程序设计',5,'科学401',18,0,2,NULL),(13,'英语',1,'X302',16,1,3,NULL),(14,'数学',4,'505',5,1,3,NULL),(15,'13门外语',5,'21B',6,1,6,NULL),(42,'大学物理',2,'逸夫楼221',10,0,5,NULL),(123,'大学英语（6）',5,'41号楼',15,0,2,NULL),(234,'管理学',1,'21a',2,0,2,NULL),(1028,'软件需求与项目管理',7,'未定',2,1,2,NULL),(1203,'软件代码开发技术',3,'11号楼 125大',8,1,2,NULL),(1204,'逆向工程',4,'学院机房',8,0,1,NULL),(1205,'专业实习',1,'机房',2,0,2,NULL),(1206,'考核',1,'实践中心',8,0,2,NULL),(1207,'英语考核',5,'41号楼',6,0,1,NULL),(1210,'nihao',2,'大学物理',2,0,2,NULL),(2006,'大学英语口语(二)',2,'2704',14,0,3,NULL),(2007,'健美操',3,'体育馆3号门',15,0,2,NULL),(2008,'概率论与数理统计',3,'21B111大教室',16,0,3,NULL),(4000,'思想道德修养与法律基础',1,'A315多',8,0,2,NULL),(4001,'高级语言程序设计',3,'训1447（实验室1）',9,1,2,NULL),(4003,'一元微积分',3,'E517多',8,0,4,NULL),(4005,'test',NULL,NULL,NULL,0,NULL,NULL),(4006,'test2',NULL,NULL,NULL,0,NULL,NULL),(4007,'test3',NULL,NULL,NULL,0,NULL,NULL);

/*Table structure for table `coursestudent` */

DROP TABLE IF EXISTS `coursestudent`;

CREATE TABLE `coursestudent` (
  `cid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0表示正在上课、1表示已经结束（即打完分）',
  `score` float DEFAULT NULL COMMENT '某个学生该课程的分数',
  `tid` int(11) DEFAULT '0' COMMENT '教师的编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coursestudent` */

insert  into `coursestudent`(`cid`,`sid`,`type`,`score`,`tid`) values (1,1002,0,NULL,1003),(10,1002,1,96.6,NULL),(11,2018201301,0,NULL,NULL),(11,1002,0,NULL,1003),(1203,1002,1,NULL,NULL),(13,1002,0,NULL,1000),(15,1002,1,66,1002),(234,1002,1,99,1002),(123,1002,1,23,1001),(1204,1002,0,NULL,1003),(42,1002,1,96,1003),(1028,2018201310,0,NULL,1001),(4001,2018201310,1,96,2003),(4007,1002,0,NULL,1002),(4007,1018071629,0,NULL,1002),(4007,2018030105,0,NULL,1002),(4007,2018201229,0,NULL,1002),(4007,2018201230,0,NULL,1002),(4007,2018201301,0,NULL,1002),(4007,2018201302,0,NULL,1002),(4007,2018201303,0,NULL,1002),(4007,2018201304,0,NULL,1002),(4007,2018201305,0,NULL,1002),(4007,2018201306,0,NULL,1002),(4007,2018201307,0,NULL,1002),(4007,2018201308,0,NULL,1002),(4007,2018201309,0,NULL,1002),(4007,2018201310,0,NULL,1002),(4007,2018201323,0,NULL,1002),(1204,1002,0,NULL,0),(1204,1018071629,0,NULL,0),(1204,2018030105,0,NULL,0),(1204,2018201229,0,NULL,0),(1204,2018201230,0,NULL,0),(1204,2018201301,0,NULL,0),(1204,2018201302,0,NULL,0),(1204,2018201303,0,NULL,0),(1204,2018201304,0,NULL,0),(1204,2018201305,0,NULL,0),(1204,2018201306,0,NULL,0),(1204,2018201307,0,NULL,0),(1204,2018201308,0,NULL,0),(1204,2018201309,0,NULL,0),(1204,2018201310,0,NULL,0),(1204,2018201323,0,NULL,0),(2008,1002,0,NULL,2001),(2008,1018071629,0,NULL,2001),(2008,2018030105,0,NULL,2001),(2008,2018201229,0,NULL,2001),(2008,2018201230,0,NULL,2001),(2008,2018201301,0,NULL,2001),(2008,2018201302,0,NULL,2001),(2008,2018201303,0,NULL,2001),(2008,2018201304,0,NULL,2001),(2008,2018201305,0,NULL,2001),(2008,2018201306,0,NULL,2001),(2008,2018201307,0,NULL,2001),(2008,2018201308,0,NULL,2001),(2008,2018201309,0,NULL,2001),(2008,2018201310,0,NULL,2001),(2008,2018201323,0,NULL,2001),(13,2018201323,0,NULL,1000),(123,2018201323,1,23,1001),(10,1018071629,0,NULL,1002),(13,1018071629,0,NULL,1000);

/*Table structure for table `depature` */

DROP TABLE IF EXISTS `depature`;

CREATE TABLE `depature` (
  `dId` int(11) NOT NULL COMMENT '院系的编号',
  `dName` char(100) DEFAULT NULL COMMENT '院系的名称',
  PRIMARY KEY (`dId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `depature` */

insert  into `depature`(`dId`,`dName`) values (1,'船舶工程学院'),(2,'建筑工程学院'),(3,'动力与能源工程学院'),(4,'自动化学院'),(5,'水声工程学院'),(6,'计算机科学与技术学院'),(7,'机电工程学院'),(8,'信息与通信工程学院'),(9,'经济管理学院'),(10,'材料科学与化学工程学院'),(11,'理学院'),(12,'外语系'),(13,'人文社会科学学院'),(14,'核学院'),(16,'体育部'),(20,'软件学院');

/*Table structure for table `detail` */

DROP TABLE IF EXISTS `detail`;

CREATE TABLE `detail` (
  `sId` int(10) NOT NULL,
  `cId` int(10) NOT NULL,
  `tId` int(10) NOT NULL,
  `status` int(11) DEFAULT NULL COMMENT '表示该课的状态，如果为0则表示未开始、为1表示正在进行、为2表示未打分、为3表示以结束',
  `score` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detail` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sId` int(10) NOT NULL AUTO_INCREMENT,
  `sName` char(100) NOT NULL,
  `sSex` int(11) DEFAULT NULL,
  `sBirth` datetime DEFAULT NULL,
  `sEntryTime` datetime DEFAULT NULL,
  `sDepature` int(11) DEFAULT NULL,
  `avatar` char(200) DEFAULT NULL,
  PRIMARY KEY (`sId`)
) ENGINE=InnoDB AUTO_INCREMENT=2018201324 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`sId`,`sName`,`sSex`,`sBirth`,`sEntryTime`,`sDepature`,`avatar`) values (1002,'小H',1,'2021-06-17 16:00:00','2021-06-17 16:00:00',14,NULL),(1018071629,'祝子阳',0,'1999-12-05 16:00:00','2018-08-31 16:00:00',4,NULL),(2018030105,'辜鹏环',0,'2000-05-31 16:00:00','2018-08-31 16:00:00',3,NULL),(2018201229,'王泽宇',0,'2000-01-05 16:00:00','2018-08-31 16:00:00',1,NULL),(2018201230,'王浩轩',0,'2000-06-04 16:00:00','2018-08-31 16:00:00',2,NULL),(2018201301,'梁锋宁',0,'2001-02-15 14:03:34','2018-09-01 14:04:10',6,NULL),(2018201302,'蓝郅韬',0,'2021-06-18 14:04:56','2021-06-19 14:04:59',8,NULL),(2018201303,'潘月',1,'2021-06-07 16:00:00','2021-06-03 16:00:00',20,NULL),(2018201304,'宫浩泽',0,'2021-06-10 14:05:55','2021-06-03 14:05:57',2,NULL),(2018201305,'蔡雪玉',1,'2021-06-05 14:06:09','2021-06-17 14:06:12',3,NULL),(2018201306,'赵希朋',0,'2021-06-03 14:06:55','2021-06-09 14:06:57',4,NULL),(2018201307,'陶彦辰',0,'2021-03-30 14:07:19','2021-06-03 14:07:23',5,NULL),(2018201308,'缪林廷',1,'2021-06-10 16:00:00','2021-07-01 16:00:00',20,NULL),(2018201309,'杨帆',1,'2021-06-03 16:00:00','2021-06-26 16:00:00',20,NULL),(2018201310,'王迪',0,'1999-02-02 16:00:00','2018-08-30 16:00:00',5,'https://teachsystem-1302763867.cos.ap-nanjing.myqcloud.com/avatar/2.jpg'),(2018201323,'汪玉',1,'2021-09-03 14:24:36','2021-06-04 14:24:40',6,NULL);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `tId` int(10) NOT NULL AUTO_INCREMENT,
  `tName` char(100) NOT NULL,
  `tSex` int(11) DEFAULT NULL,
  `tBirth` datetime DEFAULT NULL,
  `tEdu` char(10) DEFAULT NULL,
  `tTitle` char(10) DEFAULT NULL,
  `tEntryTime` datetime DEFAULT NULL,
  `tDepature` int(11) DEFAULT NULL,
  `avatar` char(200) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`tId`)
) ENGINE=InnoDB AUTO_INCREMENT=2011 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`tId`,`tName`,`tSex`,`tBirth`,`tEdu`,`tTitle`,`tEntryTime`,`tDepature`,`avatar`) values (1001,'阿豪',0,'2020-02-22 15:40:33','本科','讲师','2021-07-02 15:40:47',6,NULL),(1002,'小H',1,'2021-06-09 15:07:10','本科','讲师','2021-07-09 15:07:26',8,NULL),(1003,'小豪',NULL,'2021-06-07 16:00:00','硕士','教授','2021-06-16 16:00:00',6,NULL),(1005,'张学良',0,'2021-06-03 16:00:00','博士','高级工程师','2021-06-26 16:00:00',10,NULL),(2000,'张烨',0,'1980-12-01 16:00:00','硕士','讲师','2001-12-03 16:00:00',13,NULL),(2001,'李玉霞',1,'1985-01-30 16:00:00','博士','教授','2015-05-04 16:00:00',13,NULL),(2003,'尹玲',1,'1970-08-05 16:00:00','博士','教授','1995-05-30 16:00:00',20,NULL),(2005,'王天波',0,'1985-04-26 16:00:00','博士','副教授','2010-01-05 16:00:00',11,NULL),(2006,'郭中锋',0,'1975-02-04 16:00:00','博士','高级讲师','1999-12-02 16:00:00',12,NULL),(2007,'盛宁明',0,'1988-12-05 16:00:00','硕士','讲师','2014-05-05 16:00:00',12,NULL),(2008,'邹薇',1,'1990-01-04 16:00:00','硕士','讲师','2018-05-03 16:00:00',16,'https://teachsystem-1302763867.cos.ap-nanjing.myqcloud.com/avatar/3.jpg'),(2009,'陈毓民',0,'1969-09-01 16:00:00','博士','教授','1990-03-06 16:00:00',16,NULL),(2010,'陈玉梅',1,'1980-06-02 16:00:00','硕士','讲师','2005-06-02 16:00:00',16,NULL);

/*Table structure for table `teachr-course` */

DROP TABLE IF EXISTS `teachr-course`;

CREATE TABLE `teachr-course` (
  `cId` int(10) NOT NULL,
  `tId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teachr-course` */

insert  into `teachr-course`(`cId`,`tId`) values (13,1000),(11,1002),(1,1001),(10,1002),(15,1002),(13,1003),(234,1002),(42,1003),(123,1001),(1203,1001),(1204,1003),(1205,1002),(1206,1002),(1207,1002),(1028,1001),(1210,1002),(4000,2001),(4001,2003),(4001,2003),(4001,2003),(4003,2005),(2006,2009),(2007,2008),(2008,2001);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `psw` char(100) NOT NULL,
  `role` int(11) DEFAULT NULL COMMENT '表示用户类型，0表示管理员，1表示教师、2表示学生',
  `phone` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`psw`,`role`,`phone`) values (1000,'acef501e3ceb503c',0,'15166675416'),(1001,'5e05e63c97c2771d',1,'15166675416'),(1002,'253a8499024f1f6e',1,'15166675416'),(1003,'32ef092b31b12c0a',1,'15166675416'),(1005,'70bc1241dbacfd5a',1,'15166675416'),(2000,'f18612239538ab85',1,'15166675416'),(2001,'e1ea59d806ab2cee',1,'15166675416'),(2003,'8f6ea592ec1ea0a1',1,'15166675416'),(2005,'7450c4ad2922124f',1,'15166675416'),(2006,'588b31dbaf5bbd0f',1,'15166675416'),(2007,'09ad356b25ab8382',1,'15166675416'),(2008,'14c10e62e018e3d8',1,'15166675416'),(2009,'952a29580707670b',1,'15166675416'),(2010,'4dd1c1e2945af95d',1,'15166675416'),(1002,'253a8499024f1f6e',2,'15166675416'),(1018071629,'390565f5fc3576d940b6e136b2f7d577',2,'15166675416'),(2018030105,'8d95b3be38608d5c88198865df04abf5',2,'15166675416'),(2018201229,'27dbb5a47d2db82340b6e136b2f7d577',2,'15166675416'),(2018201230,'27dbb5a47d2db8238030c2612a1aa99d',2,'15166675416'),(2018201301,'c3870fbefc50d31cc12e87b318c5d0a0',2,'15166675416'),(2018201302,'c3870fbefc50d31cafc73142e7abd2c4',2,'15166675416'),(2018201303,'c3870fbefc50d31c0cc8281a2d972610',2,'15166675416'),(2018201304,'c3870fbefc50d31c3fb078ca54be26cf',2,'15166675416'),(2018201305,'c3870fbefc50d31c88198865df04abf5',2,'15166675416'),(2018201306,'c3870fbefc50d31c50111dfa6a013c5d',2,'15166675416'),(2018201307,'c3870fbefc50d31cf62ec036e4d70405',2,'15166675416'),(2018201308,'c3870fbefc50d31c1f05d489312770f4',2,'15166675416'),(2018201309,'c3870fbefc50d31c9772a0dbfc08f8eb',2,'15166675416'),(2018201310,'c3870fbefc50d31c60de05b32c66e714',2,'15166675416'),(2018201323,'c3870fbefc50d31c8bd958642eb83b4c',2,'15166675416');

/* Trigger structure for table `admin` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoDeleteUserByAdmin` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoDeleteUserByAdmin` BEFORE DELETE ON `admin` FOR EACH ROW BEGIN
    
        DELETE 
    FROM `user`
    WHERE id=old.aid
	AND role=0;
    END */$$


DELIMITER ;

/* Trigger structure for table `course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoSetCourse` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoSetCourse` AFTER INSERT ON `course` FOR EACH ROW BEGIN
    IF(new.cType=0)
    THEN
    INSERT INTO `coursestudent`(cid,sid)
    (SELECT new.cId cid,sid sid FROM student);
    
    END IF;
    END */$$


DELIMITER ;

/* Trigger structure for table `course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoUpdateCourse` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoUpdateCourse` AFTER UPDATE ON `course` FOR EACH ROW BEGIN
    IF(new.cType=0)
    THEN
    INSERT INTO `coursestudent`(cid,sid)
    (SELECT new.cId cid,sid sid FROM student);
    
    END IF;
    END */$$


DELIMITER ;

/* Trigger structure for table `student` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoDeleteUserByStudent` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoDeleteUserByStudent` BEFORE DELETE ON `student` FOR EACH ROW BEGIN
    
    DELETE 
    FROM `user`
    WHERE id=old.sid
	AND role=2;
    END */$$


DELIMITER ;

/* Trigger structure for table `teacher` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoDeleteUserByTeacher` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoDeleteUserByTeacher` BEFORE DELETE ON `teacher` FOR EACH ROW BEGIN
    
    DELETE 
    FROM `user`
    WHERE id=old.tid
	AND role=1;
    END */$$


DELIMITER ;

/* Trigger structure for table `teachr-course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoSetCourseType` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoSetCourseType` AFTER INSERT ON `teachr-course` FOR EACH ROW BEGIN
    UPDATE `coursestudent`
    SET tid=new.tId
    WHERE cId=new.cId;
    END */$$


DELIMITER ;

/* Trigger structure for table `teachr-course` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `autoSetCourseTypeupdate` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `autoSetCourseTypeupdate` AFTER UPDATE ON `teachr-course` FOR EACH ROW BEGIN
    UPDATE `coursestudent`
    SET tid=new.tId
    WHERE cId=new.cId;
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
