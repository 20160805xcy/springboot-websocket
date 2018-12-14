/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : wolf

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-12-14 15:36:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_web
-- ----------------------------
DROP TABLE IF EXISTS `user_web`;
CREATE TABLE `user_web` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '秘密',
  `address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='聊天室用户';

-- ----------------------------
-- Records of user_web
-- ----------------------------
INSERT INTO `user_web` VALUES ('1', 'xcy', '123', '深圳');
INSERT INTO `user_web` VALUES ('2', 'jerry', '123', '江西');
INSERT INTO `user_web` VALUES ('3', 'jack', '123', '北京');
