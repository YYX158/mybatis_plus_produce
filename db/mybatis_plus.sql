/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 15/08/2022 00:07:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int NULL DEFAULT NULL COMMENT '价格',
  `version` int NULL DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (1, '外星人笔记本', 9970, 0);

-- ----------------------------
-- Table structure for t_stu
-- ----------------------------
DROP TABLE IF EXISTS `t_stu`;
CREATE TABLE `t_stu`  (
  `sid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_stu
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_delete` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1558064286546206728 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'Jack', 20, 'Jack@baomidou.com', 0, 0);
INSERT INTO `user` VALUES (3, 'Tom', 28, 'Tom@baomidou.com', 0, 1);
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'Sandy@baomidou.com', 0, 1);
INSERT INTO `user` VALUES (5, 'Billie', 24, 'Billie@baomidou.com', 0, 0);
INSERT INTO `user` VALUES (6, '张三', 24, 'zhangsan@baomidou.com', 0, 1);
INSERT INTO `user` VALUES (7, '李四', 20, 'lisi@baomidou.com', 0, 1);
INSERT INTO `user` VALUES (8, '王赛', 21, 'ws@qq.com', 0, 0);
INSERT INTO `user` VALUES (9, 'admin', 25, NULL, 0, 0);
INSERT INTO `user` VALUES (1558064286395211778, 'cbz', 22, 'cbz@qq.com', 0, 0);
INSERT INTO `user` VALUES (1558064286546206722, 'yjh', 23, 'yjh@qq.com', 0, 0);
INSERT INTO `user` VALUES (1558064286546206723, 'qll', 21, 'qll@qq.com', 0, 1);
INSERT INTO `user` VALUES (1558064286546206724, 'ccy', 23, 'ccy@qq.com', 1, 0);
INSERT INTO `user` VALUES (1558064286546206727, 'yyx', 22, 'yyx@qq.com', 0, 1);
INSERT INTO `user` VALUES (1558064286546206728, 'yyx', 23, 'yyx@qq.com', 0, 1);
INSERT INTO `user` VALUES (1558064286546206729, 'yyx', 23, 'yyx@qq.com', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
