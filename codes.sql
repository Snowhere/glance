/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : codes

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-21 18:36:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '代码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `dependency` varchar(255) DEFAULT NULL COMMENT '依赖',
  `notice` varchar(255) DEFAULT NULL COMMENT '注意',
  `apidoc` varchar(255) DEFAULT NULL COMMENT 'api',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `others` varchar(255) DEFAULT NULL COMMENT '其他',
  `submitter` bigint(20) DEFAULT NULL COMMENT '提交人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `weight` int(11) DEFAULT '0' COMMENT '排序权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of code
-- ----------------------------

-- ----------------------------
-- Table structure for code_num
-- ----------------------------
DROP TABLE IF EXISTS `code_num`;
CREATE TABLE `code_num` (
  `code_id` bigint(20) NOT NULL,
  `view` int(11) DEFAULT NULL COMMENT '查看数量',
  `good` int(11) DEFAULT NULL COMMENT '赞数量',
  `bad` int(11) DEFAULT NULL COMMENT '踩数量',
  `report` int(11) DEFAULT NULL COMMENT '举报数量',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of code_num
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('100000', 'aaaa', null, null, 'https://www.gravatar.com/avatar/2db2c4c244f8093953787d3d246b4d17', '2017-06-21 15:53:24', null);

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `type` varchar(255) DEFAULT NULL COMMENT 'auth类型',
  `identifier` varchar(255) DEFAULT NULL COMMENT '标识',
  `credential` varchar(255) DEFAULT NULL COMMENT '认证',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('1', '100000', 'local', 'aaaa', '74b87337454200d4d33f80c4663dc5e5', '2017-06-21 15:53:24');
