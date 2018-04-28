/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mytest

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-04-28 15:01:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1000', 'hh', '111', '158');
INSERT INTO `t_user` VALUES ('1001', '就', '111', '158');
INSERT INTO `t_user` VALUES ('1002', '占', '777', '12');
INSERT INTO `t_user` VALUES ('1003', '333', '777', '12');
INSERT INTO `t_user` VALUES ('1004', '444', '777', '12');
INSERT INTO `t_user` VALUES ('1005', '78', '321', '456');
INSERT INTO `t_user` VALUES ('1006', '安抚', '321', '456');
INSERT INTO `t_user` VALUES ('1007', '安抚', '321', '456');
INSERT INTO `t_user` VALUES ('1008', '33', '111', '158');
INSERT INTO `t_user` VALUES ('1009', '漳卅', '111', '158');
INSERT INTO `t_user` VALUES ('1010', '漳卅', '111', '158');
