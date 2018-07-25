/*
SQLyog Ultimate
MySQL - 5.7.22-log : Database - Sharing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`Sharing` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `Sharing`;

/*Table structure for table `s_admin` */

DROP TABLE IF EXISTS `s_admin`;

CREATE TABLE `s_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `useid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `summary` varchar(100) DEFAULT NULL COMMENT '备注',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，0 停用；1启用；9删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_admin` */

/*Table structure for table `s_comments` */

DROP TABLE IF EXISTS `s_comments`;

CREATE TABLE `s_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(32) NOT NULL COMMENT '用户编号',
  `orerid` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `informationid` varchar(32) NOT NULL COMMENT '信息编号',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `pics` varchar(500) DEFAULT NULL COMMENT '评论图片',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态，0隐藏；1正常；9删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `commentid` varchar(32) NOT NULL COMMENT '评论编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `s_comments` */

insert  into `s_comments`(`id`,`userid`,`orerid`,`informationid`,`content`,`pics`,`status`,`createtime`,`commentid`) values 
(22,'dd47e3c60200473ba40b6e535bd1a439',NULL,'2441b5a012874748b7190d7cf150918d','值得一去的地方！',NULL,1,'2018-05-25 15:00:20','4791eca82657486096335cfd3802defa'),
(23,'dd47e3c60200473ba40b6e535bd1a439',NULL,'1eb857250480412084833f18f5b95726','在这里，让你流连忘返',NULL,1,'2018-05-25 15:02:49','6f9d5d767a564dae95545fc08b53eafb'),
(24,'dd47e3c60200473ba40b6e535bd1a439',NULL,'5674a2c4989347a6828a8e343213669c','好的',NULL,1,'2018-05-26 17:35:41','1a8e688b9d134d76a880a85c313c3f23');

/*Table structure for table `s_information` */

DROP TABLE IF EXISTS `s_information`;

CREATE TABLE `s_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `informationid` varchar(32) NOT NULL COMMENT '信息编号',
  `name` varchar(5000) NOT NULL COMMENT '信息名称',
  `shortname` varchar(5000) DEFAULT NULL COMMENT '简短名称',
  `userid` varchar(32) NOT NULL COMMENT '用户编号',
  `pic` varchar(200) DEFAULT NULL COMMENT '信息图片',
  `typeid` varchar(32) NOT NULL COMMENT '信息种类',
  `typename` varchar(50) NOT NULL COMMENT '信息类别名称',
  `content` varchar(5000) NOT NULL COMMENT '信息内容',
  `summary` varchar(5000) DEFAULT NULL COMMENT '信息描述',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '信息价格',
  `important` smallint(6) DEFAULT '0' COMMENT '重要程度，0普通; 1置顶; 2推荐; 3精华;4公告',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态，0下架；1上架；3审核失败；4标识公告;9删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `s_information` */

insert  into `s_information`(`id`,`informationid`,`name`,`shortname`,`userid`,`pic`,`typeid`,`typename`,`content`,`summary`,`price`,`important`,`status`,`createtime`) values 
(34,'cca8ad22a026401aa431d559fd871b6e','Sharing 共享信息平台声明','Sharing 共享信息平台声明','dd47e3c60200473ba40b6e535bd1a439','','','','这是共享信息平台声明部分','Sharing 共享信息平台声明',0.00,4,4,'2018-05-11 11:04:06'),
(35,'2441b5a012874748b7190d7cf150918d','去丽江，你需要了解的事','去丽江，你需要了解的事','dd47e3c60200473ba40b6e535bd1a439','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sHs9eAefcSAAIy8z7dxvM83.jpeg','7','旅游','在木府里听了不少讲解，有趣！\n\n先总结下景点，个人觉得在从丽江出发看风景玉龙雪山，泸沽湖、香格里拉还是值得一去，旅途中听到对大理褒贬不一的评价，可能因为修路破坏大家游玩的心情，受影响只停留了一天，其实多待几天看些景点感受能深点，倒是不用环洱海，坐公车、叫车、拼车都很方便，几个评价高的景点走走逛逛，每次逛完了电话叫车来接就可以了，也可能我去的时间在修路，灰尘扑天盖地，也实在不太适合骑行；希望了解人文，在丽江可以去木府看看，听听讲解，丽江古镇上也有一些老建筑参观和文化学习，门不显眼，不过都能看到；大理，发言权不够，三塔应该可以玩一下，听听讲解，有助眼前所见的理解；其他自己感受吧。','去丽江，你需要了解的事',0.00,1,1,'2018-05-25 14:57:46'),
(36,'1eb857250480412084833f18f5b95726','曲奇在贵州| 俊美青岩，秀丽花溪','曲奇在贵州| 俊美青岩，秀丽花溪','dd47e3c60200473ba40b6e535bd1a439','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sHtOaAfTTHAAL6U2XJRkc70.jpeg','7','旅游','四月底因《我在贵州等你》结缘贵州，录制完距离开还有两天的时间，便到距贵阳市中心17公里的花溪就近游玩。花溪花溪，光是听名字就觉得是个浪漫优美的地方，短短两天天的匆匆一瞥，也让我对此处十分留念。\n游玩的一天的过程里不知不觉就开始羡慕起贵阳人民，不但身处气候舒爽的凉都，还有花溪这样一个旅游资源丰富的后花园，平日闷了周末闲了驾车半小时就能来公园散个心古镇长城爬个墙，不亦乐乎。','曲奇在贵州| 俊美青岩，秀丽花溪',5.00,0,1,'2018-05-25 15:02:12'),
(37,'f6e9d5fb39b24bcdab856019d9b36a27','《精灵鼠传说》评测7.5分 “鼠小弟”的冒险','《精灵鼠传说》评测7.5分 “鼠小弟”的冒险','dcb2281af0994651b1e0c4ce8e43d069','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sHvxKAQpeBAACl08FjOww068.jpg','5','游戏','　《精灵鼠传说》是一款以老鼠为主角的冒险潜入类游戏。我最早在steam热销榜看见这款游戏时，除了独特的角色设定外，它优秀的画面场景与建模也深深地吸引住了我。而当得知这款游戏接近百分之九十的内容，都是主创Lionel \"Seith\" Gallat一人耗时三年完成时，它给我带来的惊喜和期待则变得更为浓郁。\n　　可惜的是，这份惊喜并没能一直持续下去，在数小时的流程过后，小老鼠带给我的惊喜荡然无存，面对重复度极高的关卡流程和行动模式，我感到十分的枯燥与无聊。这虽说还不至于彻底毁了整个游戏的体验，但或多或少与我期待中的那个美轮美奂的童话世界还有着不少差距。\n    《精灵鼠传说》的世界观非常有趣。在这个国度中，适应力和繁殖能力强的啮齿类动物，成为了主要的国民。国家由老鼠国王建立和统治，他们拥有自己的军队、语言、科技，甚至还有完善的历史书籍记录着历代老鼠国发生大小时间。虽然国家中也有类似青蛙和鸟类的存在，但是他们更像是以寓言故事里为主角提供帮助的NPC，大多数是为了生物多样性做贡献。','《精灵鼠传说》评测7.5分 “鼠小弟”的冒险',3.00,1,1,'2018-05-25 15:45:37'),
(38,'5826e8c55aa04ca9bcb901411b473372','《冰汽时代》游民评测8.8分 优雅地撑过世界末日','《冰汽时代》游民评测8.8分 优雅地撑过世界末日','dcb2281af0994651b1e0c4ce8e43d069','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sHwCyAMiyDAAEQjZYV7oM922.jpg','5','游戏','《冰汽时代》是《这是我的战争》开发商11bit的最新力作，也是最近半年内我最喜欢的策略游戏。它的体量或许远远不如《文明6》那么气势恢宏、包罗万象，但是在城建、生存、探索、经营等几个细分领域内，却打磨得非常讨喜。游戏背景设定在虚构的19世纪末，那时候工业革命正在如火如荼地进行，但是一场突如其来的全球性气温骤降彻底摧毁了人类文明。而你需要做的就是凭借蒸汽时代的技术，带领人类最后一批幸存者建立城市、确保物资、完善分工、安抚人心，从而挨过越来越寒冷的严冬。\n　　冷峻而细腻的艺术风格让游戏极具视觉冲击力，也让游玩的过程备具沉浸感。整个游戏画面几乎都被白茫茫的积雪覆盖，但是你依然能看到在街道、房舍或工作场所里瑟瑟发抖的人群，能看到工人们外出时在积雪的原野上踏出的“人行道”，能看到夜间房舍里散发出的微弱灯火。巨大而醒目的温度计布置在顶部UI的正中央，零下20度已经算是这个世界中最友好的气温。随着温度逐渐下降，画风也会伴随着狂风和暴雪变得越来越深邃模糊，当气温低至疯狂的零下150度时，整个屏幕几乎都变成了一块即将裂开的冰块。','《冰汽时代》游民评测8.8分 优雅地撑过世界末日',0.00,2,1,'2018-05-25 15:50:17'),
(39,'83fef50b51b3433eb50e756fa6fb542a','Judea Pearl  AI','Judea Pearl  AI','dcb2281af0994651b1e0c4ce8e43d069','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sH7V-AQkfEAACcWpZDBP0548.jpg','6','科技','网易科技讯 5月25日消息，据大西洋月刊报道，人工智能（AI）的智慧很大程度上应归功于朱迪亚·珀尔（Judea Pearl）。在20世纪80年代，他领导的努力使机器可依据概率进行推理。然而现在，珀尔却成为AI领域最尖锐的批评者之一。在最新著作《The Book of Why：The New Science of Cause and Effect》中，珀尔认为由于未能充分理解智能的真正含义，AI的发展已受到阻碍。\n      30年前，AI研究的一个主要挑战是对机器进行编程，以便将潜在的原因与一系列可观察的条件联系起来。珀尔想出了使用贝叶斯网络（Bayesian networks）解决这个问题的方法。贝叶斯网络使机器有了更强的推理能力，假设有个病人从非洲回来，并伴随有发烧和全身疼痛的症状，机器认为最有可能的解释就是他患上了疟疾。2011年，珀尔获得了计算机科学的最高荣誉——图灵奖（Turing Award），在很大程度上要归功于贝叶斯网络。','Judea Pearl  AI',0.00,3,1,'2018-05-25 19:08:28'),
(40,'61815f26ec2c4b3f919be0dd6b97f768','test','test','dcb2281af0994651b1e0c4ce8e43d069','','1','饮食','test','test',2.00,0,1,'2018-05-26 13:37:52'),
(41,'fa078f03ee16498a84522371daa8ff2c','迷上芦笋那原生的青嫩 收藏','迷上芦笋那原生的青嫩 收藏','50b55f83e1a44f20ad00463dbb8f5574','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sI_geANHH0AAJ6CknlKwQ323.jpg','1','饮食','芦笋，原名“石刁柏”，是多年生草本植物。因外形酷似芦苇的嫩芽和竹笋，故而得名“芦笋”。芦笋含有丰富的维生素B、维生素A以及叶酸、硒、铁、锰、锌等微量元素，还具有人体所必需的各种氨基酸，在国际上被公认为“世界十大名菜”之一，享有“蔬菜之王”的美誉，备受欧洲营养学家和减肥人士青睐。\n\n芦笋入菜，脆嫩清甜','迷上芦笋那原生的青嫩 收藏',2.00,0,1,'2018-05-26 14:26:27'),
(42,'5674a2c4989347a6828a8e343213669c','创意灯饰，让颜控无话可说','这些创意灯饰，让颜控无话可说','dcb2281af0994651b1e0c4ce8e43d069','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sJF3CANEy9AAE3edZKm6o527.jpg','8','文艺','后现代时尚简约创意圆球艺术设计师客厅样板房卧室书房装饰落地灯','这些创意灯饰，让颜控无话可说',4.00,0,1,'2018-05-26 16:15:06'),
(43,'813d03ae850d4dd8bc26768c47c97d17','test','test','dd47e3c60200473ba40b6e535bd1a439','http://image.arexstorm.top/group1/M00/00/00/wKgBP1sJLC6Ab9AvAAE3edZKm6o964.jpg','1','饮食','test','test',0.00,0,1,'2018-05-26 17:43:27');

/*Table structure for table `s_informationtype` */

DROP TABLE IF EXISTS `s_informationtype`;

CREATE TABLE `s_informationtype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT '' COMMENT '名称',
  `shortname` varchar(20) DEFAULT '' COMMENT '简称',
  `icon` varchar(200) DEFAULT '' COMMENT '图标',
  `summary` varchar(500) DEFAULT '' COMMENT '备注',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态，0停用；1使用；9删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `informationtypeid` varchar(32) NOT NULL COMMENT '信息类型编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `informationtypeid_un` (`informationtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `s_informationtype` */

insert  into `s_informationtype`(`id`,`name`,`shortname`,`icon`,`summary`,`status`,`createtime`,`lasttime`,`informationtypeid`) values 
(1,'饮食','饮食','','饮食',1,'2018-03-18 13:15:35','2018-05-25 10:40:42','1'),
(2,'游戏','游戏','','游戏',1,'2018-04-19 13:59:07','2018-05-25 10:40:49','5'),
(7,'科技','科技','','科技',1,'2018-05-24 14:24:05','2018-05-24 14:24:05','6'),
(8,'旅游','旅游','','旅游',1,'2018-05-24 14:24:27','2018-05-24 14:24:27','7'),
(9,'文艺','文艺','','文艺',1,'2018-05-24 14:24:44','2018-05-24 14:24:44','8'),
(10,'teset','test','','test',0,'2018-05-26 16:15:53','2018-05-26 16:15:53','9');

/*Table structure for table `s_order` */

DROP TABLE IF EXISTS `s_order`;

CREATE TABLE `s_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `orderid` varchar(32) NOT NULL COMMENT '订单编号',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '购买数',
  `back` smallint(6) NOT NULL DEFAULT '0' COMMENT '退货，0购买；1退货；2换货',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，0停用；1使用；9删除',
  `buyerid` varchar(32) NOT NULL COMMENT '买家用户编号',
  `sellerid` varchar(32) NOT NULL COMMENT '买家用户编号',
  `amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `s_order` */

insert  into `s_order`(`id`,`orderid`,`count`,`back`,`createtime`,`lasttime`,`status`,`buyerid`,`sellerid`,`amount`) values 
(9,'2e7bba7c92cf4923b6b9b797b0647c70',1,0,'2018-05-26 18:11:32','2018-05-26 18:11:32',1,'dd47e3c60200473ba40b6e535bd1a439','dcb2281af0994651b1e0c4ce8e43d069',3.00);

/*Table structure for table `s_orderdetail` */

DROP TABLE IF EXISTS `s_orderdetail`;

CREATE TABLE `s_orderdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `orderdetailid` varchar(32) NOT NULL COMMENT '订单明细编号',
  `orderid` varchar(32) NOT NULL COMMENT '订单编号',
  `informationid` varchar(32) NOT NULL COMMENT '信息编号',
  `buyerid` varchar(32) NOT NULL COMMENT '买家编号',
  `sellerid` varchar(32) NOT NULL COMMENT '卖家编号',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '购买数量',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，0停用；1使用；9删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '\n\n更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `s_orderdetail` */

insert  into `s_orderdetail`(`id`,`orderdetailid`,`orderid`,`informationid`,`buyerid`,`sellerid`,`count`,`status`,`createtime`,`lasttime`) values 
(9,'b426b697a0df464b8604a42e8e84f9ba','2e7bba7c92cf4923b6b9b797b0647c70','f6e9d5fb39b24bcdab856019d9b36a27','dd47e3c60200473ba40b6e535bd1a439','dcb2281af0994651b1e0c4ce8e43d069',1,0,'2018-05-26 18:11:32','2018-05-26 18:11:32');

/*Table structure for table `s_pic_path` */

DROP TABLE IF EXISTS `s_pic_path`;

CREATE TABLE `s_pic_path` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `path` varchar(200) NOT NULL COMMENT '访问路径',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态, 0停用; 1启用；9删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(20) NOT NULL COMMENT '图片的类型',
  `picpathid` varchar(32) NOT NULL COMMENT '图片路径编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

/*Data for the table `s_pic_path` */

insert  into `s_pic_path`(`id`,`name`,`path`,`status`,`createtime`,`lasttime`,`type`,`picpathid`) values 
(69,'丽江.jpeg','group1/M00/00/00/wKgBP1sHs9eAefcSAAIy8z7dxvM83.jpeg',1,'2018-05-25 14:57:27','2018-05-25 14:57:27','','117f870bee194f0b9a8914a1b11d283a'),
(70,'贵州.jpeg','group1/M00/00/00/wKgBP1sHtOaAfTTHAAL6U2XJRkc70.jpeg',1,'2018-05-25 15:01:58','2018-05-25 15:01:58','','3ced9e500fd04855970fa07e97ffb960'),
(71,'游戏.jpg','group1/M00/00/00/wKgBP1sHvxKAQpeBAACl08FjOww068.jpg',1,'2018-05-25 15:45:22','2018-05-25 15:45:22','','8c4ade5c49bd4d8688bc478b42055057'),
(72,'游戏2.jpg','group1/M00/00/00/wKgBP1sHwCyAMiyDAAEQjZYV7oM922.jpg',1,'2018-05-25 15:50:04','2018-05-25 15:50:04','','a3da678cf1ee4065aa61aee305b6d5e1'),
(73,'科技.jpg','group1/M00/00/00/wKgBP1sH7QqAeIjaAACcWpZDBP0978.jpg',1,'2018-05-25 19:01:31','2018-05-25 19:01:31','','9c5b6862038347fc83a7cb38eab8237a'),
(74,'科技.jpg','group1/M00/00/00/wKgBP1sH7V-AQkfEAACcWpZDBP0548.jpg',1,'2018-05-25 19:02:55','2018-05-25 19:02:55','','060b7fa4c4e6444d88fdbeb9cf8fed84'),
(75,'美食.jpg','group1/M00/00/00/wKgBP1sI_geANHH0AAJ6CknlKwQ323.jpg',1,'2018-05-26 14:26:15','2018-05-26 14:26:15','','78ae9bc0235b4b20a576e5cee5315688'),
(76,'文艺.jpg','group1/M00/00/00/wKgBP1sJF3CANEy9AAE3edZKm6o527.jpg',1,'2018-05-26 16:14:40','2018-05-26 16:14:40','','9587c29fcf9e42deb01784f747cd8c3b'),
(77,'文艺.jpg','group1/M00/00/00/wKgBP1sJLC6Ab9AvAAE3edZKm6o964.jpg',1,'2018-05-26 17:43:10','2018-05-26 17:43:10','','b741ae63cf2a4375a1212de2ca5c59fa');

/*Table structure for table `s_user` */

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态,0停用；1启用；9停用',
  `summary` varchar(50) DEFAULT NULL COMMENT '说明',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `credit` decimal(10,2) DEFAULT '60.00' COMMENT '信用度，默认是60',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `roleid` varchar(32) DEFAULT NULL COMMENT '角色id',
  `token` varchar(200) DEFAULT NULL COMMENT '设备id',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户账户S币',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `s_user` */

insert  into `s_user`(`id`,`userid`,`phone`,`email`,`nickname`,`createtime`,`lasttime`,`status`,`summary`,`password`,`avatar`,`credit`,`sex`,`city`,`roleid`,`token`,`amount`) values 
(1,'dd47e3c60200473ba40b6e535bd1a439','15956949297','sandiegoe@126.com','沈召权','2018-03-14 20:41:35','2018-05-26 16:02:44',1,'追求个性。。。','0a4381259536095b252fd2c636dd8e1c','http://image.arexstorm.top/group1/M00/00/00/wKgBP1riAi-AJ5wLAAPzytMSFD8530.png',60.00,'男','安徽省合肥市蜀山区','admin',NULL,9974.90),
(24,'dcb2281af0994651b1e0c4ce8e43d069',NULL,'xiebo@126.com','xiebo','2018-05-25 14:48:30','2018-05-25 14:48:30',1,NULL,'e10adc3949ba59abbe56e057f20f883e','/images/avatar/default.png',60.00,NULL,NULL,'user',NULL,22.00),
(31,'50b55f83e1a44f20ad00463dbb8f5574',NULL,'km@126.com','km','2018-05-26 14:21:34','2018-05-26 14:21:34',0,NULL,'e10adc3949ba59abbe56e057f20f883e','/images/avatar/default.png',60.00,NULL,NULL,'user',NULL,4.00);

/*Table structure for table `s_user_bind` */

DROP TABLE IF EXISTS `s_user_bind`;

CREATE TABLE `s_user_bind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '绑定类型，0 邮箱；1 手机号',
  `phone` varchar(32) DEFAULT '' COMMENT '绑定手机号',
  `email` varchar(32) DEFAULT '' COMMENT '绑定邮箱',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `s_user_bind` */

insert  into `s_user_bind`(`id`,`userid`,`type`,`phone`,`email`,`createtime`,`lasttime`) values 
(1,'dd47e3c60200473ba40b6e535bd1a439',0,'','sandiegoe@126.com','2018-04-25 00:12:44','2018-04-25 00:12:44');

/*Table structure for table `s_user_sign` */

DROP TABLE IF EXISTS `s_user_sign`;

CREATE TABLE `s_user_sign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(32) NOT NULL COMMENT '用户编号',
  `days` int(11) NOT NULL DEFAULT '0' COMMENT '连续天数',
  `points` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户每天签到积分',
  `sign` bigint(20) NOT NULL DEFAULT '0' COMMENT '签到的总天数',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `s_user_sign` */

insert  into `s_user_sign`(`id`,`userid`,`days`,`points`,`sign`,`createtime`,`lasttime`) values 
(6,'dd47e3c60200473ba40b6e535bd1a439',1,5.00,1,'2018-05-26 00:23:05','2018-05-26 00:23:05');

/*Table structure for table `s_user_sign_log` */

DROP TABLE IF EXISTS `s_user_sign_log`;

CREATE TABLE `s_user_sign_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(32) NOT NULL COMMENT '用户编号',
  `days` int(11) NOT NULL DEFAULT '0' COMMENT '连续天数',
  `points` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户每天签到积分',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `s_user_sign_log` */

insert  into `s_user_sign_log`(`id`,`userid`,`days`,`points`,`createtime`) values 
(23,'dd47e3c60200473ba40b6e535bd1a439',1,5.00,'2018-05-26 00:23:06');

/*Table structure for table `s_verifycode` */

DROP TABLE IF EXISTS `s_verifycode`;

CREATE TABLE `s_verifycode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '类型，0 邮箱;1手机号',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(50) DEFAULT '' COMMENT '手机号',
  `code` varchar(100) NOT NULL DEFAULT '' COMMENT '验证码',
  `times` smallint(6) NOT NULL DEFAULT '0' COMMENT '错误次数',
  `validtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '有效时间',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态，0未验证；1验证成功；2验证失败；9删除；',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `s_verifycode` */

/*Table structure for table `sys_dictionary` */

DROP TABLE IF EXISTS `sys_dictionary`;

CREATE TABLE `sys_dictionary` (
  `dict_code` varchar(32) NOT NULL COMMENT '字典主键',
  `dict_name` varchar(50) NOT NULL COMMENT '字典名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典';

/*Data for the table `sys_dictionary` */

insert  into `sys_dictionary`(`dict_code`,`dict_name`,`description`) values 
('1','性别',NULL);

/*Table structure for table `sys_dictionarydata` */

DROP TABLE IF EXISTS `sys_dictionarydata`;

CREATE TABLE `sys_dictionarydata` (
  `dictdata_code` varchar(32) NOT NULL COMMENT '字典项主键',
  `dict_code` varchar(32) NOT NULL COMMENT '字典主键',
  `dictdata_name` varchar(50) NOT NULL COMMENT '字典项值',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `sort_number` int(11) NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`dictdata_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='字典项';

/*Data for the table `sys_dictionarydata` */

insert  into `sys_dictionarydata`(`dictdata_code`,`dict_code`,`dictdata_name`,`is_delete`,`sort_number`,`description`) values 
('D3J2tkwu','1','男',0,0,NULL),
('fSkvu3XK','1','女',0,1,NULL);

/*Table structure for table `sys_login_record` */

DROP TABLE IF EXISTS `sys_login_record`;

CREATE TABLE `sys_login_record` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `os_name` varchar(200) DEFAULT NULL COMMENT '操作系统',
  `device` varchar(200) DEFAULT NULL COMMENT '设备名',
  `browser_type` varchar(200) DEFAULT NULL COMMENT '浏览器类型',
  `ip_address` varchar(200) DEFAULT NULL COMMENT 'ip地址',
  `create_time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_login_record` */

insert  into `sys_login_record`(`id`,`user_id`,`os_name`,`device`,`browser_type`,`ip_address`,`create_time`) values 
('4e9d8f1ea7ce46a8866dc955b8e2fd0e','dd47e3c60200473ba40b6e535bd1a439','Windows 10','Windows 10','Chrome','60.168.232.247','2018-05-26 13:16:41'),
('6fc71721b6bc429b96b4ee4cd46480df','dd47e3c60200473ba40b6e535bd1a439','Windows 10','Windows 10','Chrome','60.168.232.247','2018-05-25 22:23:17');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `permission_id` varchar(32) NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级权限',
  `permission_name` varchar(20) NOT NULL COMMENT '权限名',
  `permission_value` varchar(20) DEFAULT NULL COMMENT '权限值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除：0否,1是',
  `permission_icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `order_number` int(11) NOT NULL DEFAULT '0' COMMENT '菜单排序编号',
  `permission_type` int(11) NOT NULL DEFAULT '0' COMMENT '权限类型：0菜单，1按钮',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`permission_id`,`parent_id`,`permission_name`,`permission_value`,`create_time`,`update_time`,`is_delete`,`permission_icon`,`order_number`,`permission_type`) values 
('5zLoLbDq','X0K27xEi','角色管理','system/role','2017-04-15 12:47:52','2018-02-23 09:35:37',0,NULL,2,0),
('6ow0AXFF','X0K27xEi','用户管理','system/user','2017-04-08 13:54:23','2018-02-14 19:37:18',0,NULL,1,0),
('75283226c8fd4f8ca905f8cbda0f2a44','f094cd3912794a348c66120e198d41f2','用户信息管理','information/info','2018-05-02 21:12:56','2018-05-02 21:14:19',0,NULL,6,0),
('ad381a1f0f0d42fb925cf367db1c8055','d448ad56247d48a3a710b7bea7545b29','评论管理','order/comments','2018-05-02 21:15:15','2018-05-05 10:09:52',0,NULL,10,0),
('d448ad56247d48a3a710b7bea7545b29','0','订单管理',NULL,'2018-05-02 21:13:50','2018-05-02 23:09:58',0,'&#xe668;',8,0),
('e893d3b0a6cb47d2b7fc805b539c1035','d448ad56247d48a3a710b7bea7545b29','用户订单管理','order/order','2018-05-02 21:14:47','2018-05-02 21:16:09',0,NULL,9,0),
('f094cd3912794a348c66120e198d41f2','0','信息管理',NULL,'2018-05-02 21:11:54','2018-05-02 22:12:22',0,'&#xe611;',5,0),
('fb7d9569cc9649a6902e44bf58d03a03','f094cd3912794a348c66120e198d41f2','信息类型管理','information/type','2018-05-02 21:13:21','2018-05-02 21:14:44',0,NULL,7,0),
('SQMnxrNU','X0K27xEi','权限管理','system/permission','2017-04-14 13:18:02','2018-02-23 09:35:42',0,NULL,3,0),
('w9F4aXyx','X0K27xEi','登录日志','system/loginRecord','2017-07-24 13:41:13','2018-02-23 09:35:50',0,NULL,4,0),
('X0K27xEi','0','系统管理',NULL,'2017-04-08 13:42:46','2018-02-23 09:35:30',0,'&#xe716;',0,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`comments`,`create_time`,`update_time`,`is_delete`) values 
('admin','管理员','系统管理员','2018-02-23 08:31:22','2018-02-23 11:18:26',0),
('user','普通用户','系统普通用户','2018-02-23 08:32:11','2018-02-23 11:19:08',0);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`) values 
('14511f299e1146e4b8795e72ec21d363','admin','e893d3b0a6cb47d2b7fc805b539c1035'),
('1b2eca1cbccf4bcca818dd66d0219edf','user','fb7d9569cc9649a6902e44bf58d03a03'),
('2daff0e78bb04e30bcab1efc1953ed80','user','f094cd3912794a348c66120e198d41f2'),
('3e56a3ec400a47b18257d3035b2c4b30','user','w9F4aXyx'),
('42a2cefdbbe643c784d43856aa9c78c4','admin','X0K27xEi'),
('525bb665e6d9499cacf6425f3e017fef','admin','SQMnxrNU'),
('690fd7e049a24f799bfc1210033b0893','user','ad381a1f0f0d42fb925cf367db1c8055'),
('6f009ba285bd4b6990485e2b8e5160ec','admin','fb7d9569cc9649a6902e44bf58d03a03'),
('772ce96cd7534feaa3688c1079b31584','admin','d448ad56247d48a3a710b7bea7545b29'),
('8010d61c8b5245ca98e468b61f28e941','admin','6ow0AXFF'),
('80a470fbfb804bd690c4b794e5855fad','admin','75283226c8fd4f8ca905f8cbda0f2a44'),
('8481cf25c9a3422cb2c1ad4086e1453d','admin','f094cd3912794a348c66120e198d41f2'),
('998624222ac64cd381ccd58365e3752a','admin','w9F4aXyx'),
('a959adb9269e4359bfcbfbd4c6953c90','user','6ow0AXFF'),
('ada5ce07567047b692b1ccb260eb47f2','user','e893d3b0a6cb47d2b7fc805b539c1035'),
('ae2714f2726b4683958bc8f893170761','admin','ad381a1f0f0d42fb925cf367db1c8055'),
('b6b942ec4e4f47ae8ff0d3041b448103','user','75283226c8fd4f8ca905f8cbda0f2a44'),
('d165f2b4b824417599cc76bf74d7933a','user','d448ad56247d48a3a710b7bea7545b29'),
('dcf62fd13b674836b9b605768ae04068','user','X0K27xEi'),
('dd87cea1ca3745ddac2be803a82b3ded','admin','5zLoLbDq');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
