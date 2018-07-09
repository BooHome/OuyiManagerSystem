/*
 Navicat Premium Data Transfer

 Source Server         : 欧一控股
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : oysystem

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 09/07/2018 09:42:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for co_admins
-- ----------------------------
DROP TABLE IF EXISTS `co_admins`;
CREATE TABLE `co_admins`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userPwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_config
-- ----------------------------
DROP TABLE IF EXISTS `co_config`;
CREATE TABLE `co_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cTitle` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cCopyright` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_members
-- ----------------------------
DROP TABLE IF EXISTS `co_members`;
CREATE TABLE `co_members`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mProductID` int(11) UNSIGNED DEFAULT NULL,
  `mTel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mAddTime` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 405 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_productclass
-- ----------------------------
DROP TABLE IF EXISTS `co_productclass`;
CREATE TABLE `co_productclass`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cSort` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_products
-- ----------------------------
DROP TABLE IF EXISTS `co_products`;
CREATE TABLE `co_products`  (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `pcid` int(11) NOT NULL,
  `pTitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pTitleImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pRemark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pED` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pFDSJ` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pLX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pJKQX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pNum` int(11) DEFAULT NULL,
  `pUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pState` tinyint(2) NOT NULL,
  `pHitNum` int(11) UNSIGNED ZEROFILL DEFAULT 00000000000,
  `pRegNum` int(11) DEFAULT NULL,
  `pSortID` int(11) NOT NULL,
  `pAddTime` date NOT NULL,
  `pActivate` int(1) UNSIGNED ZEROFILL DEFAULT 0,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_regist
-- ----------------------------
DROP TABLE IF EXISTS `co_regist`;
CREATE TABLE `co_regist`  (
  `id` int(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cpId` int(16) DEFAULT NULL,
  `pV` int(11) DEFAULT NULL,
  `uV` int(11) DEFAULT NULL,
  `cAddTime` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 142 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_regist_1
-- ----------------------------
DROP TABLE IF EXISTS `co_regist_1`;
CREATE TABLE `co_regist_1`  (
  `id` int(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pTitle` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pV` int(11) DEFAULT NULL,
  `uV` int(11) DEFAULT NULL,
  `cAddTime` date DEFAULT NULL,
  `CActivate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_sapmember
-- ----------------------------
DROP TABLE IF EXISTS `co_sapmember`;
CREATE TABLE `co_sapmember`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `mSapId` int(11) DEFAULT NULL,
  `mIpAdress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mAddTime` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22945 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_saproduct
-- ----------------------------
DROP TABLE IF EXISTS `co_saproduct`;
CREATE TABLE `co_saproduct`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PV` int(11) DEFAULT NULL,
  `UV` int(11) DEFAULT NULL,
  `cAddTime` date DEFAULT NULL,
  `saProductId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6023 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_slides
-- ----------------------------
DROP TABLE IF EXISTS `co_slides`;
CREATE TABLE `co_slides`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `scid` int(11) DEFAULT NULL,
  `sTitle` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sTitleImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sSort` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sAddTime` date DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_standaloneproduct
-- ----------------------------
DROP TABLE IF EXISTS `co_standaloneproduct`;
CREATE TABLE `co_standaloneproduct`  (
  `sapId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sapName` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sapActivate` int(11) DEFAULT NULL,
  PRIMARY KEY (`sapId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for co_user
-- ----------------------------
DROP TABLE IF EXISTS `co_user`;
CREATE TABLE `co_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uTel` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pCount` int(11) DEFAULT NULL,
  `uAddTime` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 236 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
