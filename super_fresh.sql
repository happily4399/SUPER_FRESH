/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50514
Source Host           : localhost:3306
Source Database       : super_fresh

Target Server Type    : MYSQL
Target Server Version : 50514
File Encoding         : 65001

Date: 2020-07-13 22:34:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_num` varchar(20) NOT NULL,
  `admin_name` varchar(20) NOT NULL,
  `admin_pwd` varchar(20) NOT NULL,
  `admin_state` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('000', '001', '0', '在职');
INSERT INTO `admin` VALUES ('12345', '李乐', '123456', '注销');
INSERT INTO `admin` VALUES ('31802323', 'sakura', 'dsa123', '注销');
INSERT INTO `admin` VALUES ('31802328', '滕飞', 'sa123', '注销');
INSERT INTO `admin` VALUES ('332744349', 'lile', 'dsa123', '在职');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `Coupon_num` int(11) NOT NULL AUTO_INCREMENT,
  `Coupon_con` varchar(30) NOT NULL,
  `app_amount` float NOT NULL,
  `Ded_amount` float NOT NULL,
  `Coupon_start_date` datetime NOT NULL,
  `Coupon_end_date` datetime NOT NULL,
  PRIMARY KEY (`Coupon_num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('1', '十周年优惠券', '45', '20', '2020-07-01 00:00:00', '2020-07-04 00:00:00');
INSERT INTO `coupon` VALUES ('2', '十周年优惠券', '45', '20', '2020-07-06 00:00:00', '2030-07-10 00:00:00');
INSERT INTO `coupon` VALUES ('3', '四周年', '45', '45', '2030-07-05 00:00:00', '2030-07-10 00:00:00');
INSERT INTO `coupon` VALUES ('4', '腾讯优惠', '0.5', '0.5', '2020-07-05 00:00:00', '2021-09-05 00:00:00');
INSERT INTO `coupon` VALUES ('10', '生鲜网超优惠', '45', '20', '2020-07-01 00:00:00', '2020-07-15 00:00:00');

-- ----------------------------
-- Table structure for discount_infor
-- ----------------------------
DROP TABLE IF EXISTS `discount_infor`;
CREATE TABLE `discount_infor` (
  `Dis_num` int(11) NOT NULL AUTO_INCREMENT,
  `Dis_content` varchar(50) NOT NULL,
  `Dis_count` int(11) NOT NULL,
  `discount` float NOT NULL,
  `Dis_start_date` datetime NOT NULL,
  `Dis_end_date` datetime NOT NULL,
  PRIMARY KEY (`Dis_num`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount_infor
-- ----------------------------
INSERT INTO `discount_infor` VALUES ('2', '一周年', '2', '0.4', '2020-07-01 00:00:00', '2020-07-05 00:00:00');
INSERT INTO `discount_infor` VALUES ('3', '周年大折扣', '20', '0.5', '2020-07-09 00:00:00', '2020-07-11 00:00:00');
INSERT INTO `discount_infor` VALUES ('4', '-1周年大折扣', '20', '0.5', '2020-07-04 00:00:00', '2020-07-11 00:00:00');
INSERT INTO `discount_infor` VALUES ('6', '午后小茶', '20', '0.7', '2020-07-11 00:00:00', '2020-09-01 00:00:00');

-- ----------------------------
-- Table structure for fresh_category
-- ----------------------------
DROP TABLE IF EXISTS `fresh_category`;
CREATE TABLE `fresh_category` (
  `Category_number` int(11) NOT NULL AUTO_INCREMENT,
  `Category_name` varchar(20) NOT NULL,
  `Category_des` varchar(50) NOT NULL,
  PRIMARY KEY (`Category_number`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fresh_category
-- ----------------------------
INSERT INTO `fresh_category` VALUES ('1', '樱花花', '这个不辣');
INSERT INTO `fresh_category` VALUES ('2', '海鲜', '海里的');
INSERT INTO `fresh_category` VALUES ('4', '花朵儿', '樱花儿');
INSERT INTO `fresh_category` VALUES ('6', '测试类别', '测试');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_num` int(11) NOT NULL AUTO_INCREMENT,
  `Category_number` int(11) NOT NULL,
  `goods_name` varchar(10) NOT NULL,
  `goods_price` float NOT NULL,
  `vip_price` float DEFAULT NULL,
  `goods_count` int(11) NOT NULL,
  `goods_spe` varchar(20) DEFAULT NULL,
  `goods_det` varchar(20) DEFAULT NULL,
  `sales_count` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`goods_num`),
  KEY `FK_商品分类` (`Category_number`),
  CONSTRAINT `FK_商品分类` FOREIGN KEY (`Category_number`) REFERENCES `fresh_category` (`Category_number`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2', '1', '汉堡', '17.5', '0', '168', '个', '洋垃圾', '00000000000');
INSERT INTO `goods` VALUES ('4', '1', 'balabala', '22.5', '11', '20', '个', '就是巴拉巴拉', '00000000000');
INSERT INTO `goods` VALUES ('5', '2', '鱼鱼鱼', '22.2', '0', '162', '条', '就是摸鱼', '00000000000');
INSERT INTO `goods` VALUES ('6', '1', '樱花', '21', '11', '100', '朵', '21331', '00000000000');
INSERT INTO `goods` VALUES ('9', '2', '龙虾', '222', '111', '420', '只', '4546', '00000000000');
INSERT INTO `goods` VALUES ('12', '4', '花朵儿', '22', '11', '102', '朵', '描述额', '00000000000');
INSERT INTO `goods` VALUES ('16', '6', '测试商品', '50', '45', '60', '个', '好吃', '00000000120');

-- ----------------------------
-- Table structure for goods_discount
-- ----------------------------
DROP TABLE IF EXISTS `goods_discount`;
CREATE TABLE `goods_discount` (
  `goods_num` int(11) NOT NULL,
  `Dis_num` int(11) NOT NULL,
  `start_count` datetime DEFAULT NULL,
  `end_count` datetime DEFAULT NULL,
  PRIMARY KEY (`goods_num`,`Dis_num`),
  KEY `FK_Goods_discount2` (`Dis_num`),
  CONSTRAINT `FK_Goods_discount` FOREIGN KEY (`goods_num`) REFERENCES `goods` (`goods_num`),
  CONSTRAINT `FK_Goods_discount2` FOREIGN KEY (`Dis_num`) REFERENCES `discount_infor` (`Dis_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_discount
-- ----------------------------
INSERT INTO `goods_discount` VALUES ('2', '4', '2020-07-05 00:00:00', '2020-07-09 00:00:00');
INSERT INTO `goods_discount` VALUES ('4', '3', '2020-07-09 00:00:00', '2020-07-11 00:00:00');
INSERT INTO `goods_discount` VALUES ('4', '6', '2020-07-11 00:00:00', '2020-08-01 00:00:00');
INSERT INTO `goods_discount` VALUES ('5', '3', '2020-07-09 00:00:00', '2020-07-11 00:00:00');
INSERT INTO `goods_discount` VALUES ('16', '6', '2020-07-13 00:00:00', '2020-08-01 00:00:00');

-- ----------------------------
-- Table structure for goods_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `goods_evaluation`;
CREATE TABLE `goods_evaluation` (
  `order_num` int(11) NOT NULL,
  `User_num` int(11) NOT NULL,
  `goods_num` int(11) NOT NULL,
  `eva_con` varchar(50) NOT NULL,
  `eva_date` datetime NOT NULL,
  `star` int(11) NOT NULL,
  `photo` varchar(80) DEFAULT NULL,
  KEY `FK_Goods_evaluation2` (`goods_num`),
  KEY `fk` (`order_num`),
  KEY `kk` (`User_num`),
  CONSTRAINT `fk` FOREIGN KEY (`order_num`) REFERENCES `goods_order` (`order_num`),
  CONSTRAINT `FK_Goods_evaluation2` FOREIGN KEY (`goods_num`) REFERENCES `goods` (`goods_num`),
  CONSTRAINT `kk` FOREIGN KEY (`User_num`) REFERENCES `user` (`User_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_evaluation
-- ----------------------------
INSERT INTO `goods_evaluation` VALUES ('24', '2', '2', '好吃', '2020-07-13 22:16:02', '5', null);

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order` (
  `order_num` int(11) NOT NULL AUTO_INCREMENT,
  `User_num` int(11) DEFAULT NULL,
  `ship_num` int(11) DEFAULT NULL,
  `Coupon_num` int(11) DEFAULT NULL,
  `Ori_price` float DEFAULT NULL,
  `Fin_price` float DEFAULT NULL,
  `Service_time` datetime DEFAULT NULL,
  `Order_state` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`order_num`),
  KEY `FK_商品优惠` (`Coupon_num`),
  KEY `FK_商品配送地址` (`ship_num`),
  KEY `FK_购买商品` (`User_num`),
  CONSTRAINT `FK_购买商品` FOREIGN KEY (`User_num`) REFERENCES `user` (`User_num`),
  CONSTRAINT `FK_商品配送地址` FOREIGN KEY (`ship_num`) REFERENCES `shipping` (`ship_num`),
  CONSTRAINT `FK_商品优惠` FOREIGN KEY (`Coupon_num`) REFERENCES `coupon` (`Coupon_num`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_order
-- ----------------------------
INSERT INTO `goods_order` VALUES ('9', '1', null, null, null, null, null, '退货');
INSERT INTO `goods_order` VALUES ('17', '2', '2', '2', '154', '134', '2020-07-19 21:53:22', '已送达');
INSERT INTO `goods_order` VALUES ('19', '2', '2', '2', '357', '337', '2020-07-19 21:51:22', '退货');
INSERT INTO `goods_order` VALUES ('20', '4', '3', '2', '875', '855', '2020-10-01 12:16:39', '退货');
INSERT INTO `goods_order` VALUES ('21', '4', '3', null, '6580', '6580', '2020-10-10 00:00:00', '退货');
INSERT INTO `goods_order` VALUES ('22', '4', null, null, '0', '0', null, '购物车中');
INSERT INTO `goods_order` VALUES ('23', '4', null, null, null, null, null, '购物车中');
INSERT INTO `goods_order` VALUES ('24', '2', '2', null, '1750', '1750', '2020-07-13 21:22:22', '已送达');
INSERT INTO `goods_order` VALUES ('25', '2', '2', null, '350', '350', '2020-07-13 22:01:22', '下单');
INSERT INTO `goods_order` VALUES ('26', '5', '4', '2', '2771.5', '2751.5', '2020-11-05 12:12:12', '下单');
INSERT INTO `goods_order` VALUES ('27', '5', '4', '1', '2559', '2539', '2020-08-08 08:08:08', '退货');
INSERT INTO `goods_order` VALUES ('28', '5', '4', '1', '110', '90', '2020-07-14 00:00:00', '下单');
INSERT INTO `goods_order` VALUES ('30', '2', '2', null, '560', '560', '2020-07-15 22:11:22', '下单');
INSERT INTO `goods_order` VALUES ('31', '2', '2', null, '560', '560', '2020-07-15 22:22:22', '下单');
INSERT INTO `goods_order` VALUES ('32', '2', '2', '2', '1715', '1695', '2020-07-17 22:01:00', '退货');

-- ----------------------------
-- Table structure for goods_recipe
-- ----------------------------
DROP TABLE IF EXISTS `goods_recipe`;
CREATE TABLE `goods_recipe` (
  `goods_num` int(11) NOT NULL,
  `Recipe_num` int(11) NOT NULL,
  `Recipe_des` varchar(50) NOT NULL,
  PRIMARY KEY (`goods_num`,`Recipe_num`),
  KEY `FK_Goods_Recipe2` (`Recipe_num`),
  CONSTRAINT `FK_Goods_Recipe` FOREIGN KEY (`goods_num`) REFERENCES `goods` (`goods_num`),
  CONSTRAINT `FK_Goods_Recipe2` FOREIGN KEY (`Recipe_num`) REFERENCES `recipe` (`Recipe_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_recipe
-- ----------------------------
INSERT INTO `goods_recipe` VALUES ('2', '1', 'sakura');

-- ----------------------------
-- Table structure for good_buy
-- ----------------------------
DROP TABLE IF EXISTS `good_buy`;
CREATE TABLE `good_buy` (
  `buy_num` int(11) NOT NULL AUTO_INCREMENT,
  `goods_num` int(11) DEFAULT NULL,
  `admin_num` varchar(20) DEFAULT NULL,
  `buy_count` int(11) NOT NULL,
  `buy_state` varchar(20) NOT NULL,
  PRIMARY KEY (`buy_num`),
  KEY `FK_管理员采购` (`admin_num`),
  KEY `FK_采购` (`goods_num`),
  CONSTRAINT `FK_采购` FOREIGN KEY (`goods_num`) REFERENCES `goods` (`goods_num`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_buy
-- ----------------------------
INSERT INTO `good_buy` VALUES ('5', '4', '332744349', '100', '下单');
INSERT INTO `good_buy` VALUES ('6', '9', '332744349', '200', '入库');
INSERT INTO `good_buy` VALUES ('7', '9', '332744349', '200', '入库');
INSERT INTO `good_buy` VALUES ('8', '6', '332744349', '100', '入库');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `order_num` int(11) NOT NULL,
  `Dis_num` int(11) DEFAULT NULL,
  `goods_num` int(11) NOT NULL,
  `order_count` int(11) NOT NULL,
  `order_price` float NOT NULL DEFAULT '0',
  `order_dis` float DEFAULT NULL,
  KEY `FK_Order_detail2` (`Dis_num`),
  KEY `FK_Order_detail3` (`goods_num`),
  KEY `FK3` (`order_num`),
  CONSTRAINT `FK3` FOREIGN KEY (`order_num`) REFERENCES `goods_order` (`order_num`),
  CONSTRAINT `FK_Order_detail2` FOREIGN KEY (`Dis_num`) REFERENCES `discount_infor` (`Dis_num`),
  CONSTRAINT `FK_Order_detail3` FOREIGN KEY (`goods_num`) REFERENCES `goods` (`goods_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('17', '6', '4', '20', '154', '0.7');
INSERT INTO `order_detail` VALUES ('19', '6', '4', '50', '357', '0.7');
INSERT INTO `order_detail` VALUES ('20', null, '2', '50', '875', '0');
INSERT INTO `order_detail` VALUES ('21', null, '2', '100', '3290', '0');
INSERT INTO `order_detail` VALUES ('21', null, '2', '188', '3290', '0');
INSERT INTO `order_detail` VALUES ('24', null, '2', '100', '1750', '0');
INSERT INTO `order_detail` VALUES ('19', null, '2', '20', '350', '0');
INSERT INTO `order_detail` VALUES ('25', null, '2', '20', '350', '0');
INSERT INTO `order_detail` VALUES ('26', '6', '4', '50', '787.5', '0.7');
INSERT INTO `order_detail` VALUES ('26', null, '12', '22', '484', '0');
INSERT INTO `order_detail` VALUES ('26', null, '6', '100', '1500', '0');
INSERT INTO `order_detail` VALUES ('27', null, '12', '102', '2244', '0');
INSERT INTO `order_detail` VALUES ('27', '6', '4', '20', '315', '0.7');
INSERT INTO `order_detail` VALUES ('28', null, '12', '10', '110', '0');
INSERT INTO `order_detail` VALUES ('30', '6', '16', '20', '560', '0.7');
INSERT INTO `order_detail` VALUES ('31', '6', '16', '20', '560', '0.7');
INSERT INTO `order_detail` VALUES ('32', '6', '16', '60', '1715', '0.7');

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `Pro_num` int(11) NOT NULL AUTO_INCREMENT,
  `Pro_name` varchar(20) NOT NULL,
  `goods_num` int(11) NOT NULL,
  `Pro_price` float NOT NULL,
  `Pro_count` int(11) NOT NULL,
  `Pro_start_date` datetime NOT NULL,
  `Pro_end_date` datetime NOT NULL,
  PRIMARY KEY (`Pro_num`),
  KEY `FK_商品促销` (`goods_num`),
  CONSTRAINT `FK_商品促销` FOREIGN KEY (`goods_num`) REFERENCES `goods` (`goods_num`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of promotion
-- ----------------------------
INSERT INTO `promotion` VALUES ('13', 'sakura', '4', '9', '20', '2020-07-05 00:00:00', '2020-07-13 00:00:00');
INSERT INTO `promotion` VALUES ('14', ' ', '6', '15', '100', '2020-01-01 00:00:00', '2020-08-01 00:00:00');
INSERT INTO `promotion` VALUES ('17', '答辩结束促销', '2', '10', '20', '2020-07-01 00:00:00', '2020-07-14 00:00:00');
INSERT INTO `promotion` VALUES ('18', '促销测试', '16', '40', '50', '2020-07-13 00:00:00', '2020-07-19 00:00:00');

-- ----------------------------
-- Table structure for recipe
-- ----------------------------
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe` (
  `Recipe_num` int(11) NOT NULL AUTO_INCREMENT,
  `Recipe_name` varchar(20) NOT NULL,
  `Recipe_mater` varchar(50) NOT NULL,
  `Recipe_step` varchar(80) DEFAULT NULL,
  `Recipe_picture` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`Recipe_num`,`Recipe_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipe
-- ----------------------------
INSERT INTO `recipe` VALUES ('1', '龙虾大餐', '龙虾', '龙', '');
INSERT INTO `recipe` VALUES ('2', 'sakura', '樱花', '摘花', 'E:\\\\java photo\\\\timg.jpg');
INSERT INTO `recipe` VALUES ('3', '测试名称', '测试用料', '测试步骤', '');

-- ----------------------------
-- Table structure for shipping
-- ----------------------------
DROP TABLE IF EXISTS `shipping`;
CREATE TABLE `shipping` (
  `ship_num` int(11) NOT NULL AUTO_INCREMENT,
  `User_num` int(11) NOT NULL,
  `ship_pro` varchar(20) NOT NULL,
  `ship_city` varchar(20) NOT NULL,
  `ship_area` varchar(20) NOT NULL,
  `ship_address` varchar(20) NOT NULL,
  `ship_con` varchar(20) NOT NULL,
  `ship_tele` varchar(20) NOT NULL,
  PRIMARY KEY (`ship_num`),
  KEY `FK_用户地址` (`User_num`),
  CONSTRAINT `FK_用户地址` FOREIGN KEY (`User_num`) REFERENCES `user` (`User_num`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipping
-- ----------------------------
INSERT INTO `shipping` VALUES ('2', '2', '广东省', '惠州儿', '惠城区', 'bababab', '李乐', '1536389');
INSERT INTO `shipping` VALUES ('3', '4', '浙江', '杭州', '拱墅', '湖州街51号', '橙留香', '17812312345');
INSERT INTO `shipping` VALUES ('4', '5', 'why', 'xxx', 'xx', 'xx', 'x', 'x');
INSERT INTO `shipping` VALUES ('5', '2', '浙江省', '杭州市', '拱墅区', 'zucc', '李乐', '15363897990');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `User_num` int(11) NOT NULL AUTO_INCREMENT,
  `User_name` varchar(30) NOT NULL,
  `User_sex` varchar(10) NOT NULL,
  `User_pwd` varchar(30) NOT NULL,
  `User_Pnum` varchar(20) NOT NULL,
  `User_email` varchar(20) NOT NULL,
  `User_city` varchar(20) NOT NULL,
  `User_reg_date` datetime NOT NULL,
  `User_vip` int(11) DEFAULT NULL,
  `vip_ddl` datetime DEFAULT NULL,
  `User_state` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`User_num`,`User_Pnum`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '滕飞', '女', 'dsa123', '15363897990', '332744349@qq.com', '杭州', '2020-07-04 08:20:56', '0', null, null);
INSERT INTO `user` VALUES ('2', '李乐', '男', '123456', '31803225', '31803225@stu', '杭州', '2020-07-04 09:49:53', '1', '2020-08-31 00:00:00', '在库');
INSERT INTO `user` VALUES ('3', '李乐', '男', 'dsa15363897990', '15306584177', '332744349@qq.com', '杭州', '2020-07-04 20:15:41', '0', null, '在库');
INSERT INTO `user` VALUES ('4', '李乐111', '男', '123', '17812312345', '123456789@qq.com', '杭州', '2020-07-12 22:24:03', null, null, '在库');
INSERT INTO `user` VALUES ('5', '李乐', '男', 'dsa123', '15360196778', 'sakura@qq', '杭州', '2020-07-13 11:04:31', '1', '2020-08-08 00:00:00', '在库');
INSERT INTO `user` VALUES ('6', '李乐', '男', '123456', '123456', '33274349', '杭州', '2020-07-13 20:26:41', null, null, '在库');
