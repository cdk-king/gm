/*
 Navicat Premium Data Transfer

 Source Server         : tb
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : gm

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 14/01/2019 14:01:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_ban_ip
-- ----------------------------
DROP TABLE IF EXISTS `t_ban_ip`;
CREATE TABLE `t_ban_ip`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `serverId` int(20) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `banLong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `banDatetime` datetime(0) NULL DEFAULT NULL,
  `banState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cdk
-- ----------------------------
DROP TABLE IF EXISTS `t_cdk`;
CREATE TABLE `t_cdk`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `couponId` int(20) NULL DEFAULT NULL,
  `sequenceId` int(20) NOT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `cdk` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isUsed` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `configKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'key是关键字',
  `configValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_config
-- ----------------------------
INSERT INTO `t_config` VALUES (1, 'touristId', '3');
INSERT INTO `t_config` VALUES (2, 'touristName', '游客');

-- ----------------------------
-- Table structure for t_coupon
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `giftId` int(20) NULL DEFAULT NULL,
  `couponId` int(20) NULL DEFAULT NULL,
  `couponCount` int(20) NULL DEFAULT NULL,
  `couponTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coupon_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `start_sequence` int(20) NULL DEFAULT NULL,
  `end_sequence` int(20) NULL DEFAULT NULL,
  `salt` int(20) NULL DEFAULT NULL,
  `starDatetime` datetime(0) NULL DEFAULT NULL,
  `endDatetime` datetime(0) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `fileUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_datasource`;
CREATE TABLE `t_datasource`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `dataSource_id` int(20) NULL DEFAULT NULL,
  `dataSource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSource_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_datasource
-- ----------------------------
INSERT INTO `t_datasource` VALUES (1, 1, 1, 1, 'jdbc:mysql://192.168.1.16:3306/dbdiablomuzhilog?serverTimezone=Asia/Shanghai', 'root', '123456', '2018-12-26 16:40:57', 'cdk', 0);

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileOldName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileSize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `downloadTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileDescribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` int(255) NULL DEFAULT NULL,
  `fileType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_game
-- ----------------------------
DROP TABLE IF EXISTS `t_game`;
CREATE TABLE `t_game`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gameName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gameTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `game_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameEncryptSign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(20) NOT NULL DEFAULT 0,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(20) NOT NULL DEFAULT 0,
  `serverApi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_game
-- ----------------------------
INSERT INTO `t_game` VALUES (1, '魔幻ARPG', 'game1', '魔幻ARPG', 'game1', 0, '2018-12-07 11:10:21', 'cdk', 0, 'http://123.207.115.217:21234/serversByJson', 0);

-- ----------------------------
-- Table structure for t_gameplatform
-- ----------------------------
DROP TABLE IF EXISTS `t_gameplatform`;
CREATE TABLE `t_gameplatform`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platformId` int(20) NULL DEFAULT NULL,
  `platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gameId` int(20) NOT NULL,
  `roleId` int(20) NULL DEFAULT NULL,
  `platformTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `platform_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentId` int(11) NULL DEFAULT NULL,
  `state` int(20) NOT NULL DEFAULT 0,
  `sort` int(20) NOT NULL DEFAULT 0,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) NOT NULL DEFAULT 0,
  `dataSource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSource_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSource_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_gameplatform
-- ----------------------------
INSERT INTO `t_gameplatform` VALUES (1, 1, '平台1', 1, 3, 'muzhi', '平台1', NULL, 0, 0, 'cdk', '2018-12-19 16:32:49', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_gameserver
-- ----------------------------
DROP TABLE IF EXISTS `t_gameserver`;
CREATE TABLE `t_gameserver`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serverId` int(20) NULL DEFAULT NULL,
  `server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `serverIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `serverPort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `platformTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `server_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(20) NOT NULL DEFAULT 0,
  `sort` int(255) NOT NULL DEFAULT 0,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(255) NOT NULL DEFAULT 0,
  `isDefault` int(20) NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `openServiceTime` datetime(0) NULL DEFAULT NULL,
  `channel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_gameserver
-- ----------------------------
INSERT INTO `t_gameserver` VALUES (1, 1, '测试服', 's1.dtest.xssyx.com', '4000', 1, 1, 'muzhi', '测试服', 0, 0, 'cdk', '2019-01-14 11:23:52', 0, 0, '1', '2019-01-08 00:00:00', '');

-- ----------------------------
-- Table structure for t_gift
-- ----------------------------
DROP TABLE IF EXISTS `t_gift`;
CREATE TABLE `t_gift`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `giftName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gift_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `giftTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `giftType` int(20) NULL DEFAULT NULL,
  `giftValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `propId` int(11) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `state` int(20) NULL DEFAULT NULL,
  `sort` int(20) NULL DEFAULT NULL,
  `isDelete` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_gift_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_gift_upload`;
CREATE TABLE `t_gift_upload`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `giftId` int(20) NULL DEFAULT NULL,
  `giftName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `giftDescribe` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `giftValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `limitCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expire_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_prize1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value_prize1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_gift_upload
-- ----------------------------
INSERT INTO `t_gift_upload` VALUES (1, 1, '公会礼包', '100万金币（绑）[*]5，护送令牌（绑）[*]3，1.5倍经验药水（绑）[*]3，初级光翼精华（绑）[*]5，初级圣物精华（绑）[*]5', NULL, 1, 1, '999', '2019_11_10', '420100;count=5;binded', '');
INSERT INTO `t_gift_upload` VALUES (2, 2, '豪华礼包', '坐骑进阶丹（绑）[*]10，佣兵进阶丹（绑）[*]10，初级光翼精华（绑）[*]5，1.5倍经验药水（绑）[*]1，离线挂机卡（绑）[*]1', NULL, 1, 1, '999', '2019_11_20', '6020001;count=10;binded', '');
INSERT INTO `t_gift_upload` VALUES (3, 3, 'VIP礼包', '初级光翼精华(绑) [*] 5、佣兵进阶丹(绑) [*] 10、2倍经验药水(绑) [*] 1、100万金币(绑) [*] 3、橙色符文精华宝箱(绑) [*] 3', NULL, 1, 1, '999', '2019_11_30', '2020004;count=5;binded', '');
INSERT INTO `t_gift_upload` VALUES (4, 4, '温暖新手礼包', '坐骑进阶丹(绑) [*] 5、佣兵进阶丹(绑) [*] 5、100万金币(绑) [*] 3、1朵玫瑰(绑) [*] 3', NULL, 1, 1, '999', '2019_12_10', '6020001;count=5;binded', '');
INSERT INTO `t_gift_upload` VALUES (5, 5, '温暖日常礼包', '坐骑进阶丹(绑) [*] 10、初级翅膀精华(绑) [*] 3、100万铜钱(绑) [*] 3', NULL, 1, 1, '999', '2019_12_10', '6020001;count=10;binded', '');
INSERT INTO `t_gift_upload` VALUES (6, 6, '温暖特权礼包', '2倍经验药(绑) [*] 1、100万铜钱(绑) [*] 3、护花令(绑) [*] 3', NULL, 1, 1, '999', '2019_12_10', '270007;count=1;binded', '');

-- ----------------------------
-- Table structure for t_platform_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_channel`;
CREATE TABLE `t_platform_channel`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `channelId` int(20) NULL DEFAULT NULL,
  `channelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channelTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) UNSIGNED NULL DEFAULT NULL,
  `channel_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_platform_channel
-- ----------------------------
INSERT INTO `t_platform_channel` VALUES (1, 1, 1, 1, '渠道1', 'channel1', 'cdk', '2019-01-14 11:32:20', 0, '渠道1');

-- ----------------------------
-- Table structure for t_platform_email
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_email`;
CREATE TABLE `t_platform_email`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `serverList` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `emailTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emailContent` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendReason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `startDatetime` datetime(0) NULL DEFAULT NULL,
  `endDatetime` datetime(0) NULL DEFAULT NULL,
  `sendState` int(20) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT NULL,
  `errorList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_platform_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_notice`;
CREATE TABLE `t_platform_notice`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `serverList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `noticeTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `noticeContent` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `startDatetime` datetime(0) NULL DEFAULT NULL,
  `endDatetime` datetime(0) NULL DEFAULT NULL,
  `sendState` int(20) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT NULL,
  `propList` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `moneyList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `errorList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player
-- ----------------------------
DROP TABLE IF EXISTS `t_player`;
CREATE TABLE `t_player`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `playerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerId` int(20) NULL DEFAULT NULL,
  `isOnline` int(20) NULL DEFAULT NULL,
  `lastIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vipLevel` int(20) NULL DEFAULT NULL,
  `diamond` int(20) NULL DEFAULT NULL,
  `rechargeAmount` int(20) NULL DEFAULT NULL,
  `level` int(20) NULL DEFAULT NULL,
  `registrationTime` datetime(0) NULL DEFAULT NULL,
  `combatPower` int(20) NULL DEFAULT NULL,
  `addDateTime` datetime(0) NULL DEFAULT NULL,
  `isProhibitSpeak` int(20) NULL DEFAULT NULL,
  `isBan` int(20) NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `serverId` int(20) NOT NULL,
  `prohibitSpeakTime` int(20) NULL DEFAULT NULL,
  `banTime` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_ban_log
-- ----------------------------
DROP TABLE IF EXISTS `t_player_ban_log`;
CREATE TABLE `t_player_ban_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `playerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `serverId` int(20) NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isToBan` int(20) NULL DEFAULT NULL,
  `userId` int(20) NULL DEFAULT NULL,
  `banTime` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_prohibitspeak_log
-- ----------------------------
DROP TABLE IF EXISTS `t_player_prohibitspeak_log`;
CREATE TABLE `t_player_prohibitspeak_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `playerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `serverId` int(20) NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `userId` int(20) NULL DEFAULT NULL,
  `isToProhibitSpeak` int(20) NULL DEFAULT NULL,
  `prohibitSpeakTime` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_player_type
-- ----------------------------
DROP TABLE IF EXISTS `t_player_type`;
CREATE TABLE `t_player_type`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `playerType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_player_type
-- ----------------------------
INSERT INTO `t_player_type` VALUES (1, '全部');
INSERT INTO `t_player_type` VALUES (2, '玩家');
INSERT INTO `t_player_type` VALUES (3, '内部');
INSERT INTO `t_player_type` VALUES (4, '管理员');
INSERT INTO `t_player_type` VALUES (5, '新手引导员');

-- ----------------------------
-- Table structure for t_prize_valuetype
-- ----------------------------
DROP TABLE IF EXISTS `t_prize_valuetype`;
CREATE TABLE `t_prize_valuetype`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `ValueTypeId` int(20) NULL DEFAULT NULL,
  `ValueTypeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_prize_valuetype
-- ----------------------------
INSERT INTO `t_prize_valuetype` VALUES (1, 0, '经验', 1);
INSERT INTO `t_prize_valuetype` VALUES (2, 1, '充值钻石', 1);
INSERT INTO `t_prize_valuetype` VALUES (3, 2, '内部钻石', 1);
INSERT INTO `t_prize_valuetype` VALUES (4, 3, '绑定钻石', 1);
INSERT INTO `t_prize_valuetype` VALUES (5, 4, '金币', 1);
INSERT INTO `t_prize_valuetype` VALUES (6, 5, '符文精华值', 1);
INSERT INTO `t_prize_valuetype` VALUES (7, 6, '符文碎片', 1);
INSERT INTO `t_prize_valuetype` VALUES (8, 7, '荣誉值', 1);
INSERT INTO `t_prize_valuetype` VALUES (9, 8, '帮贡', 1);
INSERT INTO `t_prize_valuetype` VALUES (10, 9, '仓库积分', 1);
INSERT INTO `t_prize_valuetype` VALUES (11, 10, '装备寻宝积分', 1);

-- ----------------------------
-- Table structure for t_prop
-- ----------------------------
DROP TABLE IF EXISTS `t_prop`;
CREATE TABLE `t_prop`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `prop_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `propName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '道具名',
  `propTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `propType` int(20) NULL DEFAULT NULL COMMENT '道具类型',
  `state` int(255) NOT NULL DEFAULT 0 COMMENT '道具状态',
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `addDatetime` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `isDelete` int(255) NOT NULL DEFAULT 0 COMMENT '删除标识',
  `sort` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prop_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_prop_apply`;
CREATE TABLE `t_prop_apply`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `serverId` int(20) NOT NULL,
  `propList` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `applyType` int(20) NOT NULL,
  `releaseTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `releaseContent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerType` int(20) NULL DEFAULT NULL,
  `applyUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `applyReason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `confirmState` int(20) NULL DEFAULT NULL,
  `confirmUserId` int(20) NULL DEFAULT NULL,
  `confirmDatetime` datetime(0) NULL DEFAULT NULL,
  `addUser` int(20) NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `playerNameList` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerAccountList` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerIdList` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `moneyList` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `applyState` int(20) NULL DEFAULT NULL,
  `applyDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prop_money_type
-- ----------------------------
DROP TABLE IF EXISTS `t_prop_money_type`;
CREATE TABLE `t_prop_money_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moneyId` int(11) NULL DEFAULT NULL,
  `moneyType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_prop_money_type
-- ----------------------------
INSERT INTO `t_prop_money_type` VALUES (1, 0, '银两', 1);
INSERT INTO `t_prop_money_type` VALUES (2, 1, '金子', 1);
INSERT INTO `t_prop_money_type` VALUES (3, 2, '绑定金子', 1);
INSERT INTO `t_prop_money_type` VALUES (4, 3, ' 系统金子', 1);

-- ----------------------------
-- Table structure for t_prop_quality
-- ----------------------------
DROP TABLE IF EXISTS `t_prop_quality`;
CREATE TABLE `t_prop_quality`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `propQualityId` int(20) NULL DEFAULT NULL,
  `propQuality` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_prop_quality
-- ----------------------------
INSERT INTO `t_prop_quality` VALUES (1, 1, '白', 1);
INSERT INTO `t_prop_quality` VALUES (2, 2, '绿', 1);
INSERT INTO `t_prop_quality` VALUES (3, 3, '蓝', 1);
INSERT INTO `t_prop_quality` VALUES (4, 4, '紫', 1);
INSERT INTO `t_prop_quality` VALUES (5, 5, '橙', 1);
INSERT INTO `t_prop_quality` VALUES (6, 6, '红', 1);
INSERT INTO `t_prop_quality` VALUES (7, 7, '粉', 1);
INSERT INTO `t_prop_quality` VALUES (8, 8, '闪紫', 1);
INSERT INTO `t_prop_quality` VALUES (9, 9, '闪橙', 1);
INSERT INTO `t_prop_quality` VALUES (10, 10, '闪红 ', 1);

-- ----------------------------
-- Table structure for t_prop_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_prop_upload`;
CREATE TABLE `t_prop_upload`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `propId` int(20) NULL DEFAULT NULL,
  `propName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `propType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `propDescribe` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `propMaxCount` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_prop_upload
-- ----------------------------
INSERT INTO `t_prop_upload` VALUES (1, 220001, '1级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (2, 220002, '2级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (3, 220003, '3级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (4, 220004, '4级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (5, 220005, '5级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (6, 220006, '6级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (7, 220007, '7级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (8, 220008, '8级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (9, 220009, '9级红宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (10, 220010, '1级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (11, 220011, '2级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (12, 220012, '3级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (13, 220013, '4级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (14, 220014, '5级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (15, 220015, '6级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (16, 220016, '7级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (17, 220017, '8级蓝宝石', '24', '', 1, 1, 999);
INSERT INTO `t_prop_upload` VALUES (18, 220018, '9级蓝宝石', '24', '', 1, 1, 999);

-- ----------------------------
-- Table structure for t_prop_upload_type
-- ----------------------------
DROP TABLE IF EXISTS `t_prop_upload_type`;
CREATE TABLE `t_prop_upload_type`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `propType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `gameId` int(20) NULL DEFAULT NULL,
  `propTypeId` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_prop_upload_type
-- ----------------------------
INSERT INTO `t_prop_upload_type` VALUES (1, '装备', 1, 1, 1);
INSERT INTO `t_prop_upload_type` VALUES (2, '普通物品（程序真实使用）', 1, 1, 2);
INSERT INTO `t_prop_upload_type` VALUES (3, '礼包类', 1, 1, 3);
INSERT INTO `t_prop_upload_type` VALUES (4, '材料', 1, 1, 4);
INSERT INTO `t_prop_upload_type` VALUES (5, '一般可使用道具', 1, 1, 5);
INSERT INTO `t_prop_upload_type` VALUES (6, '虚拟显示不添加到背包的物品', 1, 1, 6);
INSERT INTO `t_prop_upload_type` VALUES (7, '佣兵经验丹', 1, 1, 7);
INSERT INTO `t_prop_upload_type` VALUES (8, '佣兵祝福丹', 1, 1, 8);
INSERT INTO `t_prop_upload_type` VALUES (9, '佣兵祝福卡', 1, 1, 9);
INSERT INTO `t_prop_upload_type` VALUES (10, '佣兵潜能丹', 1, 1, 10);
INSERT INTO `t_prop_upload_type` VALUES (11, '佣兵飞升丹', 1, 1, 11);
INSERT INTO `t_prop_upload_type` VALUES (12, '佣兵悟性丹', 1, 1, 12);
INSERT INTO `t_prop_upload_type` VALUES (13, '离线经验卡 ', 1, 1, 13);
INSERT INTO `t_prop_upload_type` VALUES (14, '坐骑祝福丹', 1, 1, 14);
INSERT INTO `t_prop_upload_type` VALUES (15, '坐骑祝福卡', 1, 1, 15);
INSERT INTO `t_prop_upload_type` VALUES (16, '坐骑潜能丹', 1, 1, 16);
INSERT INTO `t_prop_upload_type` VALUES (17, '坐骑飞升丹', 1, 1, 17);
INSERT INTO `t_prop_upload_type` VALUES (18, '坐骑悟性丹', 1, 1, 18);
INSERT INTO `t_prop_upload_type` VALUES (19, '外显升级材料 ', 1, 1, 19);
INSERT INTO `t_prop_upload_type` VALUES (20, '带有效果的材料(非可使用物品) ', 1, 1, 20);
INSERT INTO `t_prop_upload_type` VALUES (21, '普通符文', 1, 1, 21);
INSERT INTO `t_prop_upload_type` VALUES (22, '符文精华', 1, 1, 22);
INSERT INTO `t_prop_upload_type` VALUES (23, '小鬼怪', 1, 1, 23);
INSERT INTO `t_prop_upload_type` VALUES (24, '宝石', 1, 1, 24);
INSERT INTO `t_prop_upload_type` VALUES (25, '红包', 1, 1, 25);
INSERT INTO `t_prop_upload_type` VALUES (26, '爆竹', 1, 1, 26);
INSERT INTO `t_prop_upload_type` VALUES (27, 'vip卡', 1, 1, 27);

-- ----------------------------
-- Table structure for t_right
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rightName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `right_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rightParentId` int(20) NULL DEFAULT NULL,
  `rightTag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rightSort` int(20) NOT NULL,
  `state` int(20) NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES (1, '用户管理查看权限', '用户管理查看权限', 1, 'User_Management_View', 0, 0, '2018-10-16 17:28:15', 'cdk', 0);
INSERT INTO `t_right` VALUES (3, '角色管理查看权限', '角色管理查看权限', 0, 'Role_Management_View', 0, 0, '2018-10-16 17:29:15', 'cdk', 0);
INSERT INTO `t_right` VALUES (5, '权限管理查看权限', '权限管理查看权限', 0, 'Right_Management_View', 0, 0, '2018-10-16 21:06:52', 'cdk', 0);
INSERT INTO `t_right` VALUES (6, '用户管理操作权限', '用户管理操作权限', 0, 'User_management_Handle', 0, 0, '2018-10-16 21:18:12', 'cdk', 0);
INSERT INTO `t_right` VALUES (7, '角色管理操作权限', '角色管理操作权限', 0, 'Role_management_Handle', 0, 0, '2018-10-16 21:19:53', 'cdk', 0);
INSERT INTO `t_right` VALUES (8, '权限管理操作权限', '权限管理操作权限', 0, 'Right_management_Handle', 0, 0, '2018-10-16 21:20:30', 'cdk', 0);
INSERT INTO `t_right` VALUES (10, '游戏管理查看权限', '游戏管理查看权限', 0, 'Game_Management_View', 0, 0, '2018-10-20 10:51:58', 'cdk', 0);
INSERT INTO `t_right` VALUES (11, '平台管理查看权限', '平台管理查看权限', 0, 'Platform_Management_View', 0, 0, '2018-10-20 15:35:14', 'cdk', 0);
INSERT INTO `t_right` VALUES (12, '服务器管理查看权限', '服务器管理查看权限', 0, 'Server_Management_View', 0, 0, '2018-10-22 10:48:21', 'cdk', 0);
INSERT INTO `t_right` VALUES (13, '服务器管理操作权限', '服务器管理操作权限', 0, 'Server_management_Handle', 0, 0, '2018-10-22 11:32:29', 'cdk', 0);
INSERT INTO `t_right` VALUES (14, '平台管理操作权限', '平台管理操作权限', 0, 'Platform_management_Handle', 0, 0, '2018-10-22 11:33:53', 'cdk', 0);
INSERT INTO `t_right` VALUES (15, '游戏管理操作权限', '游戏管理操作权限', 0, 'Game_management_Handle', 0, 0, '2018-10-22 11:35:13', 'cdk', 0);
INSERT INTO `t_right` VALUES (20, '服务器树形查看权限', '服务器树形查看权限', 0, 'Server_Tree_View', 0, 0, '2018-11-13 17:54:45', 'cdk', 0);
INSERT INTO `t_right` VALUES (21, '玩家信息查看权限', '玩家信息查看权限', 0, 'Player_Info_View', 0, 0, '2018-11-13 18:00:16', 'cdk', 0);
INSERT INTO `t_right` VALUES (22, '玩家禁言记录查看', '玩家禁言记录查看', 0, 'Player_ProhibitSpeakLog_View', 0, 0, '2018-11-13 18:08:54', 'cdk', 0);
INSERT INTO `t_right` VALUES (23, '玩家禁封记录查看', '玩家禁封记录查看', 0, 'Player_BanLog_View', 0, 0, '2018-11-13 18:09:26', 'cdk', 0);
INSERT INTO `t_right` VALUES (25, '发送邮件查看权限', '发送邮件查看权限', 0, 'Send_Email_View', 0, 0, '2018-11-13 18:20:38', 'cdk', 0);
INSERT INTO `t_right` VALUES (26, '发送广播查看权限', '发送广播查看权限', 0, 'Send_Notice_View', 0, 0, '2018-11-13 18:21:32', 'cdk', 0);
INSERT INTO `t_right` VALUES (27, '全服邮件查看权限', '全服邮件查看权限', 0, 'All_Email_View', 0, 0, '2018-11-13 18:22:49', 'cdk', 0);
INSERT INTO `t_right` VALUES (28, '全服公告查看权限', '全服公告查看权限', 0, 'All_Notice_View', 0, 0, '2018-11-13 18:23:47', 'cdk', 0);
INSERT INTO `t_right` VALUES (30, '道具导入信息查看权限', '道具导入信息查看权限', 0, 'Prop_Upload_View', 0, 0, '2018-11-13 18:27:27', 'cdk', 0);
INSERT INTO `t_right` VALUES (31, '申请道具查看权限', '申请道具查看权限', 0, 'Apple_Prop_View', 0, 0, '2018-11-13 18:28:15', 'cdk', 0);
INSERT INTO `t_right` VALUES (32, '道具导入查看权限', '道具导入查看权限', 0, 'Upload_Prop_View', 0, 0, '2018-11-13 18:28:39', 'cdk', 0);
INSERT INTO `t_right` VALUES (34, '礼包导入信息查看权限', '礼包导入信息查看权限', 0, 'Gift_Upload_View', 0, 0, '2018-11-13 18:30:09', 'cdk', 0);
INSERT INTO `t_right` VALUES (35, '礼包导入查看权限', '礼包导入查看权限', 0, 'Upload_Gift_View', 0, 0, '2018-11-13 18:30:37', 'cdk', 0);
INSERT INTO `t_right` VALUES (36, '申请礼包激活码查看权限', '申请礼包激活码查看权限', 0, 'Apple_GiftCDK_View', 0, 0, '2018-11-13 18:31:05', 'cdk', 0);
INSERT INTO `t_right` VALUES (37, '激活码使用查看权限', '激活码使用查看权限', 0, 'CDK_Use_View', 0, 0, '2018-11-13 18:31:41', 'cdk', 0);
INSERT INTO `t_right` VALUES (38, '玩家信息操作权限', '玩家信息操作权限', 0, 'Player_Info_Handle', 0, 0, '2018-11-28 14:26:56', 'cdk', 0);
INSERT INTO `t_right` VALUES (39, 'IP禁封查看权限', 'IP禁封查看权限', 0, 'Player_BanIp_View', 0, 0, '2018-11-28 20:37:35', 'cdk', 0);
INSERT INTO `t_right` VALUES (40, 'IP禁封操作权限', 'IP禁封操作权限', 0, 'Player_BanIp_Handle', 0, 0, '2018-11-28 20:38:34', 'cdk', 0);
INSERT INTO `t_right` VALUES (41, '游客账号设置查看权限', '游客账号设置查看权限', 0, 'TouristId_Set_View', 0, 0, '2018-11-28 20:57:26', 'cdk', 0);
INSERT INTO `t_right` VALUES (42, '用户中心查看权限', '用户中心查看权限', 0, 'Center_View', 0, 0, '2018-12-03 21:05:03', 'cdk', 0);
INSERT INTO `t_right` VALUES (43, '文件中转', '文件中转', 0, 'Upload_View', 0, 0, '2018-12-15 10:58:11', 'cdk', 0);
INSERT INTO `t_right` VALUES (44, '通道管理页面查看', '通道管理页面查看', 0, 'Channel_View', 0, 0, '2018-12-20 22:34:56', 'cdk', 0);
INSERT INTO `t_right` VALUES (45, '物品流通日志查看权限', '物品流通日志查看权限', 0, 'GoodFlowLog_View', 0, 0, '2018-12-25 10:49:34', 'cdk', 0);
INSERT INTO `t_right` VALUES (46, '现金流日志查看权限', '现金流日志查看权限', 0, 'MoneyFlowLog_View', 0, 0, '2018-12-25 10:50:01', 'cdk', 0);
INSERT INTO `t_right` VALUES (47, '角色创建日志查看权限', '角色创建日志查看权限', 0, 'CreateRoleLog_View', 0, 0, '2018-12-25 10:50:49', 'cdk', 0);
INSERT INTO `t_right` VALUES (48, '角色登录日志查看权限', '角色登录日志查看权限', 0, 'RoleLoginLog_View', 0, 0, '2018-12-25 10:51:19', 'cdk', 0);
INSERT INTO `t_right` VALUES (49, '数据源管理查看权限', '数据源管理查看权限', 0, 'DataSource_View', 0, 0, '2018-12-26 16:27:55', 'cdk', 0);
INSERT INTO `t_right` VALUES (50, '数据源管理操作权限', '数据源管理操作权限', 0, 'DataSource_Handle', 0, 0, '2018-12-26 16:29:03', 'cdk', 0);
INSERT INTO `t_right` VALUES (51, '充值流水日志查看权限', '充值流水日志查看权限', 0, 'ReCharge_View', 0, 0, '2018-12-29 14:31:59', 'cdk', 0);
INSERT INTO `t_right` VALUES (52, '充值消费日志查看权限', '充值消费日志查看权限', 0, 'Shop_View', 0, 0, '2018-12-29 16:21:03', 'cdk', 0);
INSERT INTO `t_right` VALUES (53, '只能改服务器状态和修改服务器时间权限', '只能改服务器状态和修改服务器时间权限', 0, 'Server_State_Datetime_Handle', 0, 0, '2019-01-08 14:29:44', 'cdk', 0);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'describe是关键字',
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `state` int(10) NULL DEFAULT 0,
  `isDelete` int(11) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', '系统管理员', 'cdk', '2018-12-19 16:23:16', 0, 0);
INSERT INTO `t_role` VALUES (2, '游客', '游客', 'cdk', '2018-12-19 16:23:34', 0, 0);
INSERT INTO `t_role` VALUES (3, '平台1', '平台1', 'cdk', '2018-12-19 16:25:20', 0, 0);
INSERT INTO `t_role` VALUES (4, '平台2', '平台2', 'cdk', '2018-12-19 16:25:34', 0, 0);
INSERT INTO `t_role` VALUES (5, '开发人员', '开发人员', 'cdk', '2018-12-25 10:56:25', 0, 0);

-- ----------------------------
-- Table structure for t_role_rights
-- ----------------------------
DROP TABLE IF EXISTS `t_role_rights`;
CREATE TABLE `t_role_rights`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `roleId` int(20) NOT NULL,
  `rightId` int(20) NOT NULL,
  `right_type` int(20) NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_rights
-- ----------------------------
INSERT INTO `t_role_rights` VALUES (1, 1, 1, NULL, 0);
INSERT INTO `t_role_rights` VALUES (2, 1, 6, NULL, 0);
INSERT INTO `t_role_rights` VALUES (3, 1, 3, NULL, 0);
INSERT INTO `t_role_rights` VALUES (4, 1, 5, NULL, 0);
INSERT INTO `t_role_rights` VALUES (5, 1, 7, NULL, 0);
INSERT INTO `t_role_rights` VALUES (6, 1, 8, NULL, 0);
INSERT INTO `t_role_rights` VALUES (7, 1, 15, NULL, 0);
INSERT INTO `t_role_rights` VALUES (8, 1, 13, NULL, 0);
INSERT INTO `t_role_rights` VALUES (9, 1, 14, NULL, 0);
INSERT INTO `t_role_rights` VALUES (10, 1, 12, NULL, 0);
INSERT INTO `t_role_rights` VALUES (11, 1, 11, NULL, 0);
INSERT INTO `t_role_rights` VALUES (12, 1, 10, NULL, 0);
INSERT INTO `t_role_rights` VALUES (13, 1, 20, NULL, 0);
INSERT INTO `t_role_rights` VALUES (14, 3, 21, NULL, 0);
INSERT INTO `t_role_rights` VALUES (15, 3, 22, NULL, 0);
INSERT INTO `t_role_rights` VALUES (16, 3, 23, NULL, 0);
INSERT INTO `t_role_rights` VALUES (17, 3, 25, NULL, 0);
INSERT INTO `t_role_rights` VALUES (18, 3, 31, NULL, 0);
INSERT INTO `t_role_rights` VALUES (19, 3, 26, NULL, 0);
INSERT INTO `t_role_rights` VALUES (20, 3, 36, NULL, 0);
INSERT INTO `t_role_rights` VALUES (21, 3, 27, NULL, 0);
INSERT INTO `t_role_rights` VALUES (22, 3, 28, NULL, 0);
INSERT INTO `t_role_rights` VALUES (23, 3, 40, NULL, 0);
INSERT INTO `t_role_rights` VALUES (24, 3, 39, NULL, 0);
INSERT INTO `t_role_rights` VALUES (25, 3, 38, NULL, 0);
INSERT INTO `t_role_rights` VALUES (26, 3, 37, NULL, 0);
INSERT INTO `t_role_rights` VALUES (27, 3, 32, NULL, 0);
INSERT INTO `t_role_rights` VALUES (28, 3, 30, NULL, 0);
INSERT INTO `t_role_rights` VALUES (29, 3, 35, NULL, 0);
INSERT INTO `t_role_rights` VALUES (30, 3, 34, NULL, 0);
INSERT INTO `t_role_rights` VALUES (31, 1, 44, NULL, 0);
INSERT INTO `t_role_rights` VALUES (38, 1, 49, NULL, 0);
INSERT INTO `t_role_rights` VALUES (39, 1, 50, NULL, 0);
INSERT INTO `t_role_rights` VALUES (44, 3, 45, NULL, 0);
INSERT INTO `t_role_rights` VALUES (45, 3, 46, NULL, 0);
INSERT INTO `t_role_rights` VALUES (46, 3, 47, NULL, 0);
INSERT INTO `t_role_rights` VALUES (47, 3, 48, NULL, 0);
INSERT INTO `t_role_rights` VALUES (48, 3, 51, NULL, 0);
INSERT INTO `t_role_rights` VALUES (49, 3, 52, NULL, 0);
INSERT INTO `t_role_rights` VALUES (58, 5, 41, NULL, 0);
INSERT INTO `t_role_rights` VALUES (62, 5, 43, NULL, 0);

-- ----------------------------
-- Table structure for t_send_email
-- ----------------------------
DROP TABLE IF EXISTS `t_send_email`;
CREATE TABLE `t_send_email`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NULL DEFAULT NULL,
  `serverId` int(20) NULL DEFAULT NULL,
  `emailTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emailContent` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendReason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendType` int(20) NULL DEFAULT NULL COMMENT '012',
  `minLevel` int(20) NULL DEFAULT NULL,
  `maxLevel` int(20) NULL DEFAULT NULL,
  `minVipLevel` int(20) NULL DEFAULT NULL,
  `maxVipLevel` int(20) NULL DEFAULT NULL,
  `minRegistrationTime` datetime(0) NULL DEFAULT NULL,
  `maxRegistrationTime` datetime(0) NULL DEFAULT NULL,
  `isOnline` int(20) NULL DEFAULT NULL,
  `sex` int(20) NULL DEFAULT NULL COMMENT '012',
  `playerNameList` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerAccountList` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `playerIdList` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendDatetime` datetime(0) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT NULL,
  `sendState` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_send_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_send_notice`;
CREATE TABLE `t_send_notice`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `gameId` int(20) NULL DEFAULT NULL,
  `platformId` int(20) NOT NULL,
  `serverList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendType` int(20) NULL DEFAULT NULL,
  `noticeType` int(20) NULL DEFAULT NULL,
  `timeInterval` int(20) NULL DEFAULT NULL,
  `cycleTime` int(20) NULL DEFAULT NULL,
  `startDatetime` datetime(0) NULL DEFAULT NULL,
  `endDatetime` datetime(0) NULL DEFAULT NULL,
  `sendDatetime` datetime(0) NULL DEFAULT NULL,
  `noticeContent` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendState` int(255) NULL DEFAULT NULL,
  `addUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_send_notice_noticetype
-- ----------------------------
DROP TABLE IF EXISTS `t_send_notice_noticetype`;
CREATE TABLE `t_send_notice_noticetype`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `noticeType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_send_notice_noticetype
-- ----------------------------
INSERT INTO `t_send_notice_noticetype` VALUES (1, '聊天框系统消息');
INSERT INTO `t_send_notice_noticetype` VALUES (2, '滚屏信息');

-- ----------------------------
-- Table structure for t_send_notice_sendtype
-- ----------------------------
DROP TABLE IF EXISTS `t_send_notice_sendtype`;
CREATE TABLE `t_send_notice_sendtype`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sendType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_send_notice_sendtype
-- ----------------------------
INSERT INTO `t_send_notice_sendtype` VALUES (1, '即时消息');
INSERT INTO `t_send_notice_sendtype` VALUES (2, '定时消息');

-- ----------------------------
-- Table structure for t_server_config
-- ----------------------------
DROP TABLE IF EXISTS `t_server_config`;
CREATE TABLE `t_server_config`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `serverId` int(20) NULL DEFAULT NULL,
  `isDefault` int(20) NULL DEFAULT NULL,
  `state` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(10) NULL DEFAULT NULL,
  `sex` int(10) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(10) NULL DEFAULT 0,
  `addDatetime` datetime(0) NULL DEFAULT NULL,
  `lastDatetime` datetime(0) NULL DEFAULT NULL,
  `isDelete` int(10) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '超级管理员', 'cdk', 'cf902a16875a866d1f18240ffa1180b1', 'cdk', '', NULL, 1, NULL, NULL, '', '', NULL, 0, '2018-12-19 16:18:50', '2018-12-19 16:18:50', 0);
INSERT INTO `t_user` VALUES (2, '管理员', 'admin', 'cf902a16875a866d1f18240ffa1180b1', '管理员', '', NULL, 1, NULL, NULL, '', '', NULL, 0, '2018-12-19 16:22:04', '2018-12-19 16:22:04', 0);
INSERT INTO `t_user` VALUES (3, '游客', 'tourist', 'cf902a16875a866d1f18240ffa1180b1', '游客', '', NULL, 1, NULL, NULL, '', '', NULL, 0, '2018-12-19 16:22:21', '2018-12-19 16:22:21', 0);
INSERT INTO `t_user` VALUES (4, '平台1', 'platform1', 'cf902a16875a866d1f18240ffa1180b1', '平台1', '', NULL, 1, NULL, NULL, '', '', NULL, 0, '2018-12-19 16:22:41', '2018-12-19 16:22:41', 0);
INSERT INTO `t_user` VALUES (5, '平台2', 'platform2', 'cf902a16875a866d1f18240ffa1180b1', '平台2', '', NULL, 1, NULL, NULL, '', '', NULL, 0, '2019-01-07 20:51:37', '2019-01-07 20:51:37', 0);

-- ----------------------------
-- Table structure for t_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_user_roles`;
CREATE TABLE `t_user_roles`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) NULL DEFAULT NULL,
  `roleId` int(20) NULL DEFAULT NULL,
  `isDelete` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_roles
-- ----------------------------
INSERT INTO `t_user_roles` VALUES (1, 1, 1, 0);
INSERT INTO `t_user_roles` VALUES (2, 2, 1, 0);
INSERT INTO `t_user_roles` VALUES (5, 3, 2, 0);
INSERT INTO `t_user_roles` VALUES (6, 1, 3, 0);
INSERT INTO `t_user_roles` VALUES (8, 2, 3, 0);
INSERT INTO `t_user_roles` VALUES (11, 5, 4, 0);
INSERT INTO `t_user_roles` VALUES (15, 1, 5, 0);
INSERT INTO `t_user_roles` VALUES (16, 4, 3, 0);

SET FOREIGN_KEY_CHECKS = 1;
