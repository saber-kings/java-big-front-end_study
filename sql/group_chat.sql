/*
 Navicat Premium Data Transfer

 Source Server         : 家庭电脑Mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.31.32:3306
 Source Schema         : group_chat

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 08/05/2020 01:45:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gdesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES (1, '群1', 'img/group.png', '很好很热闹');
INSERT INTO `t_group` VALUES (2, '群2', 'img/group.png', '幻想');
INSERT INTO `t_group` VALUES (3, '群3', 'img/group.png', '测试');

-- ----------------------------
-- Table structure for t_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `muted` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES (1, 1, 1, 1);
INSERT INTO `t_user_group` VALUES (2, 1, 2, 0);
INSERT INTO `t_user_group` VALUES (3, 1, 3, 1);
INSERT INTO `t_user_group` VALUES (5, 2, 1, 1);
INSERT INTO `t_user_group` VALUES (9, 2, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
