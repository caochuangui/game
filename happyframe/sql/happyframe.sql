/*
Navicat MySQL Data Transfer

Source Server         : 182.254.194.101
Source Server Version : 50173
Source Host           : 182.254.194.101:3306
Source Database       : happyframe

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-02-02 16:12:39
*/
DROP DATABASE IF EXISTS `happyframe`;
CREATE DATABASE IF NOT EXISTS `happyframe`;
USE `happyframe`;

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `farm`;
CREATE TABLE `farm` (
  `farmId` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL,
  `playerId` int(11) unsigned NOT NULL,
  `plantId` int(11) unsigned NOT NULL,
  `startTime` int(20) unsigned NOT NULL
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bag_item
-- ----------------------------
DROP TABLE IF EXISTS `bag_item`;
CREATE TABLE `bag_item` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `itemId` int(10) unsigned NOT NULL,
  `playerId` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bag_item
-- ----------------------------

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `playerId` int(100) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT '',
  `level` int(10) DEFAULT '0',
  `viplevel` int(10) DEFAULT '0',
  `copper` int(10) DEFAULT '0',
  `gold` int(10) DEFAULT '0',
  `exp` int(10) DEFAULT '0',
  `bagMaxCount` int(10) DEFAULT '0',
  PRIMARY KEY (`playerId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(100) NOT NULL DEFAULT '',
  `pwd` varchar(100) DEFAULT NULL,
  `playerId` int(100) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------


DROP TABLE IF EXISTS `crop`;
CREATE TABLE `crop` (
  `corpId` int(10) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '',
  `type` int(10) NOT NULL DEFAULT 0,
  `childTime` int(10) NOT NULL DEFAULT 0,
  `childIcon` varchar(100) NOT NULL DEFAULT '',
  `childDisaster` varchar(100) NOT NULL DEFAULT '',
  
  `growTime` int(10) NOT NULL DEFAULT 0,
  `growIcon` varchar(100) NOT NULL DEFAULT '',
  `growDisaster` varchar(100) NOT NULL DEFAULT '',
  
  `ripeTime` int(10) NOT NULL DEFAULT 0,
  `ripeIcon` varchar(100) NOT NULL DEFAULT '',
  `ripeDisaster` varchar(100) NOT NULL DEFAULT '',
  
  `gainTime` int(10) NOT NULL DEFAULT 0,
  `gainIcon` varchar(100) NOT NULL DEFAULT '',
  `gainDisaster` varchar(100) NOT NULL DEFAULT '',

  `dead` int(10) NOT NULL DEFAULT 0,
  `deadIcon` varchar(100) NOT NULL DEFAULT '',
  `gainId` int(10) NOT NULL DEFAULT 0,
  
  PRIMARY KEY (`corpId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;





