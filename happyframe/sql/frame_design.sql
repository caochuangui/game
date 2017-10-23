/*
Navicat MySQL Data Transfer

Source Server         : 182.254.194.101
Source Server Version : 50173
Source Host           : 182.254.194.101:3306
Source Database       : frame_design

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-02-02 16:06:52
*/

DROP DATABASE IF EXISTS `frame_design`;
CREATE DATABASE IF NOT EXISTS `frame_design`;
USE `frame_design`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for design_lvup
-- ----------------------------
DROP TABLE IF EXISTS `design_lvup`;
CREATE TABLE `design_lvup` (
  `level` int(100) unsigned NOT NULL,
  `exp` int(10) unsigned DEFAULT '0',
  `copper` int(10) DEFAULT '0',
  `gold` int(10) DEFAULT '0',
  PRIMARY KEY (`level`),
  UNIQUE KEY `level` (`level`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of design_lvup
-- ----------------------------

-- ----------------------------
-- Table structure for design_shop
-- ----------------------------
DROP TABLE IF EXISTS `design_shop`;
CREATE TABLE `design_shop` (
  `shopId` int(10) unsigned NOT NULL,
  `itemId` int(10) unsigned NOT NULL,
  `copper` int(10) unsigned NOT NULL,
  `gold` int(10) unsigned NOT NULL,
  PRIMARY KEY (`shopId`,`itemId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of design_shop
-- ----------------------------

-- ----------------------------
-- Table structure for design_warehouse_lvup
-- ----------------------------
DROP TABLE IF EXISTS `design_warehouse_lvup`;
CREATE TABLE `design_warehouse_lvup` (
  `maxCount` int(10) unsigned DEFAULT '0',
  `upGold` int(10) unsigned DEFAULT '0',
  `upCount` int(10) unsigned DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of design_warehouse_lvup
-- ----------------------------
INSERT INTO `design_warehouse_lvup` VALUES ('500', '20', '20');
