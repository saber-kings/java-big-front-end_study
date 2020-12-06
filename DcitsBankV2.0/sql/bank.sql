/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL8.0.12
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : bank

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 05/12/2020 19:19:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `balance` decimal(28, 10) UNSIGNED NOT NULL DEFAULT 0.0000000000 COMMENT '余额',
  `status` int(4) NOT NULL DEFAULT 1 COMMENT '状态（1 启用，2 冻结）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_unique`(`account`) USING BTREE COMMENT '账号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES (1, '1016041135', '15810000001', '12345678', '程阳', 1120000.0000000000, 1);
INSERT INTO `t_account` VALUES (2, 'B12110931', '15678946532', '12345678', '李四', 9090.0000000000, 1);
INSERT INTO `t_account` VALUES (3, '1016041136', '15432568745', '12345678', '王五', 10000020000.0000000000, 1);

-- ----------------------------
-- Table structure for t_account_role
-- ----------------------------
DROP TABLE IF EXISTS `t_account_role`;
CREATE TABLE `t_account_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `aid` int(11) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account_role
-- ----------------------------
INSERT INTO `t_account_role` VALUES (1, 1, 1);
INSERT INTO `t_account_role` VALUES (2, 1, 2);
INSERT INTO `t_account_role` VALUES (3, 2, 1);
INSERT INTO `t_account_role` VALUES (4, 2, 3);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单权限名称',
  `murl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单跳转路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '修改密码', 'views/xgmm.html');
INSERT INTO `t_menu` VALUES (2, '修改信息', 'views/personInfo.html');
INSERT INTO `t_menu` VALUES (3, '存取款功能', 'views/atm.html');
INSERT INTO `t_menu` VALUES (4, '转账功能', 'views/transer.html');

-- ----------------------------
-- Table structure for t_personinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_personinfo`;
CREATE TABLE `t_personinfo`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `aid` int(4) NULL DEFAULT NULL,
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(4) NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `card` bigint(18) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_personinfo
-- ----------------------------
INSERT INTO `t_personinfo` VALUES (1, 1, '程阳', 23, '男', 320542000000000001, '南邮', '15810000001');
INSERT INTO `t_personinfo` VALUES (2, 2, '赵强', 23, '男', 320542000000000002, '湖北武汉', '15810000002');
INSERT INTO `t_personinfo` VALUES (3, 3, '叶航', 22, '男', 320542000000000003, '南邮', '15810000001');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'I类卡用户');
INSERT INTO `t_role` VALUES (2, 'II类卡用户');
INSERT INTO `t_role` VALUES (3, 'III类卡用户');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rid` int(11) NOT NULL COMMENT '角色id',
  `mid` int(11) NOT NULL COMMENT '菜单权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 2, 4);
INSERT INTO `t_role_menu` VALUES (3, 3, 4);
INSERT INTO `t_role_menu` VALUES (5, 1, 2);
INSERT INTO `t_role_menu` VALUES (6, 1, 3);
INSERT INTO `t_role_menu` VALUES (7, 1, 4);
INSERT INTO `t_role_menu` VALUES (8, 2, 1);
INSERT INTO `t_role_menu` VALUES (9, 2, 2);
INSERT INTO `t_role_menu` VALUES (10, 3, 1);

SET FOREIGN_KEY_CHECKS = 1;
