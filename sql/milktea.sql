/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : milktea

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 08/05/2020 01:49:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_custom
-- ----------------------------
DROP TABLE IF EXISTS `t_custom`;
CREATE TABLE `t_custom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_custom
-- ----------------------------
INSERT INTO `t_custom` VALUES (1, '赵六', '2020-04-16 17:42:26', '2020-04-16 19:24:25', '15062387892', 'zhaoliu@qq.com');
INSERT INTO `t_custom` VALUES (2, '远坂凛', '2020-04-16 17:59:55', '2020-04-16 17:59:55', '13689745869', 'TohsakaRin@gmail.com');
INSERT INTO `t_custom` VALUES (26, '测试', '2020-04-23 01:12:13', '2020-04-23 01:14:28', '13564899521', '11@qq.com');
INSERT INTO `t_custom` VALUES (27, '再测试', '2020-04-23 01:15:26', '2020-04-23 01:15:26', '13578966541', '11@qq.com');

-- ----------------------------
-- Table structure for t_good
-- ----------------------------
DROP TABLE IF EXISTS `t_good`;
CREATE TABLE `t_good`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `g_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_price` int(11) NOT NULL,
  `g_stuff` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'img/zw.png',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_good
-- ----------------------------
INSERT INTO `t_good` VALUES (1, '珍珠奶茶', 15, '奶茶、珍珠', '又甜又香', 'images/good_zznc.jpg');
INSERT INTO `t_good` VALUES (2, '芋泥啵啵奶茶', 10, '保密', '太好喝了', 'images/good_zznc.jpg');
INSERT INTO `t_good` VALUES (3, '香芋奶茶', 10, '香芋、奶茶', '还不错', 'images/good_zznc.jpg');
INSERT INTO `t_good` VALUES (4, '新奶茶', 10, '保密', '你试试就知道了', 'images/zw.png');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `murl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '修改密码', 'views/xgmm.html');
INSERT INTO `t_menu` VALUES (2, '查看报表', NULL);
INSERT INTO `t_menu` VALUES (3, '商品管理', 'views/goods.html');
INSERT INTO `t_menu` VALUES (4, '客户管理', 'views/customs.html');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '员工');
INSERT INTO `t_role` VALUES (2, '经理');
INSERT INTO `t_role` VALUES (3, '商品管理员');
INSERT INTO `t_role` VALUES (4, '客户管理员');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 2, 2);
INSERT INTO `t_role_menu` VALUES (3, 3, 3);
INSERT INTO `t_role_menu` VALUES (4, 4, 4);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'zhangsan', '15068971465', '12345678', '张三');
INSERT INTO `t_user` VALUES (2, 'lisi', '15678946532', '12345678', '李四');
INSERT INTO `t_user` VALUES (3, 'wangwu', '15432568745', '12345678', '王五');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 1, 2);
INSERT INTO `t_user_role` VALUES (3, 2, 1);
INSERT INTO `t_user_role` VALUES (4, 2, 3);
INSERT INTO `t_user_role` VALUES (5, 3, 1);
INSERT INTO `t_user_role` VALUES (6, 3, 4);

SET FOREIGN_KEY_CHECKS = 1;
