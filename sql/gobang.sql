/*
 Navicat Premium Data Transfer

 Source Server         : 家庭电脑Mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.31.32:3306
 Source Schema         : gobang

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 04/06/2020 15:42:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_publish
-- ----------------------------
DROP TABLE IF EXISTS `t_publish`;
CREATE TABLE `t_publish`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告内容',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除(0未删除；1已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_publish
-- ----------------------------
INSERT INTO `t_publish` VALUES (1, '测试', '测试发布公告', '2020-05-23 22:55:39', NULL, 1);

-- ----------------------------
-- Table structure for t_sign
-- ----------------------------
DROP TABLE IF EXISTS `t_sign`;
CREATE TABLE `t_sign`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `total_count` int(11) NOT NULL DEFAULT 0 COMMENT '总次数',
  `cont_count` int(11) NOT NULL DEFAULT 0 COMMENT '连续次数',
  `last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次签到日期',
  `signed_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当月已经签到的日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sign
-- ----------------------------
INSERT INTO `t_sign` VALUES (1, 1, 2, 2, '2020-05-26 13:02:05', '25,26');
INSERT INTO `t_sign` VALUES (2, 3, 1, 1, '2020-05-26 13:26:01', '26');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '昵称' COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `upwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'user/02.png' COMMENT '头像',
  `win_games` int(11) NOT NULL DEFAULT 0 COMMENT '胜利局数',
  `total_games` int(11) NOT NULL DEFAULT 0 COMMENT '总局数',
  `gold` int(11) NOT NULL DEFAULT 0 COMMENT '金币',
  `jewel` int(11) NOT NULL DEFAULT 0 COMMENT '钻石',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `weixin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `rank` int(1) NOT NULL DEFAULT 0 COMMENT '段位',
  `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '账户状态(0.已删除 1.正常 2.锁定 )',
  `newcomer` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否领过新人礼包（0.未领过；1.已领过）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'saber污妖王', '13176848575', '12345678', 'user/02.png', 0, 0, 1025, 100, NULL, NULL, NULL, NULL, 0, NULL, 1, 1);
INSERT INTO `t_user` VALUES (3, '昵称', '13176848576', '12345678', 'user/01.png', 0, 0, 1010, 100, NULL, NULL, NULL, NULL, 0, NULL, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
