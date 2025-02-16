/*
 Navicat MySQL Data Transfer

 Source Server         : 106.14.93.164
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 106.14.93.164:3306
 Source Schema         : driver

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 06/06/2021 16:25:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '管理员', 'orHQS5AehHk3uPvs2EZ8wdHnosU8');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图片',
  `is_show` int(11) NULL DEFAULT 1 COMMENT '标记是否显示',
  PRIMARY KEY (`b_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 'https://z3.ax1x.com/2021/04/27/g9L8Gn.jpg', 0);
INSERT INTO `banner` VALUES (2, 'https://z3.ax1x.com/2021/05/16/ggJ8zj.jpg', 0);
INSERT INTO `banner` VALUES (3, 'https://z3.ax1x.com/2021/05/19/g5BDqx.jpg', 0);
INSERT INTO `banner` VALUES (4, 'http://www.phoneboss.cn:8080/dsmsImage/1e30a2d0-e3e5-4ab6-9ca7-edb49ad4bd65.JPG', 0);
INSERT INTO `banner` VALUES (5, 'http://www.phoneboss.cn:8080/dsmsImage/ca94979b-03d9-4180-a1cf-6a7f3b8af191.JPG', 0);
INSERT INTO `banner` VALUES (6, 'http://www.phoneboss.cn:8080/dsmsImage/55565125-99e5-4fc3-8f61-242d0788a65b.jpg', 1);
INSERT INTO `banner` VALUES (7, 'http://www.phoneboss.cn:8080/dsmsImage/508a3db6-e1c4-423f-acb7-3712f942a866.jpg', 1);
INSERT INTO `banner` VALUES (8, 'http://www.phoneboss.cn:8080/dsmsImage/7678295a-bffd-4026-a878-23e61c757820.jpg', 1);

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price`  (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES (1, 6100.00, 'https://z3.ax1x.com/2021/04/27/g9q03t.jpg', 'C1手动');
INSERT INTO `price` VALUES (2, 6700.00, 'https://z3.ax1x.com/2021/04/27/g9qw9I.jpg', 'C2自动');

-- ----------------------------
-- Table structure for proxy
-- ----------------------------
DROP TABLE IF EXISTS `proxy`;
CREATE TABLE `proxy`  (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '代理人的用户id',
  `openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `base_price` decimal(10, 2) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`) USING BTREE,
  INDEX `weixin_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of proxy
-- ----------------------------
INSERT INTO `proxy` VALUES (1, 1, 'oaRAQ46183Mf0j2grXNAiWxVVP8k', NULL, NULL, NULL, 0.01, NULL);
INSERT INTO `proxy` VALUES (2, 2, 'oaRAQ46I7x24Tv_PWC3htcaY5RIY', NULL, NULL, NULL, 0.02, NULL);
INSERT INTO `proxy` VALUES (7, 5, NULL, '张彦通', '19901764942', '张彦通', 0.01, '松江');
INSERT INTO `proxy` VALUES (8, 6, 'oaRAQ4-F3YaJ8eCmJli5VJdHVoak', '张彦通', '19901764942', '张彦通', 0.01, '松江');
INSERT INTO `proxy` VALUES (9, 56, NULL, '张彦通', '19901764942', 'ben', 0.01, '松江');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `p_id` int(11) NULL DEFAULT 0 COMMENT '该学生的代理人是谁',
  `openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '微信id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `card_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号码',
  `car_type` int(11) NULL DEFAULT NULL COMMENT '所选车型',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '住址',
  `register_time` datetime NULL DEFAULT NULL COMMENT '报名时间',
  `pay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 2, 1, 'oaRAQ46I7x24Tv_PWC3htcaY5RIY', '恺', '19802126908', NULL, 2, '中国', '2021-06-05 16:57:08', 0.01, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(11) NULL DEFAULT 0,
  `province` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_register` int(11) NULL DEFAULT 0 COMMENT '是否报名',
  `role` int(11) NULL DEFAULT 0 COMMENT '0普通用户，1代理人',
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `weixin_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'oaRAQ46183Mf0j2grXNAiWxVVP8k', '戊六七', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXtSSl1CJPz4svc5KZYJYzXBCvcCBfKicqnjveL5gPPVYTky7iacGX68NM97cEoh0iasbdicWoaTZj1w/132', 1, '安徽', '安庆', 0, 1);
INSERT INTO `user` VALUES (2, 'oaRAQ46I7x24Tv_PWC3htcaY5RIY', '做梦笑醒', 'https://thirdwx.qlogo.cn/mmopen/vi_32/kQQ7zt3ZDC02eicpGKciatTxL1z2E8G22HQGg4GEpRicZ9VLoCG5GQXNBXk4OiaHJdhemnrHgGHDMQWTvOFicWNdgTQ/132', 0, '', '', 1, 1);
INSERT INTO `user` VALUES (3, NULL, '做梦笑醒', 'https://thirdwx.qlogo.cn/mmopen/vi_32/E9tmiaHsaESsQOvh8NbvVwu9pjIJKkSB4MRQwbKrOiauAd45Vt039MWmN921XGXZZHsy0aYibJicaYLdFWficSmMXCg/132', 0, '', '', 0, 0);
INSERT INTO `user` VALUES (4, NULL, '做梦笑醒', 'https://thirdwx.qlogo.cn/mmopen/vi_32/E9tmiaHsaESsQOvh8NbvVwu9pjIJKkSB4MRQwbKrOiauAd45Vt039MWmN921XGXZZHsy0aYibJicaYLdFWficSmMXCg/132', 0, '', '', 0, 0);
INSERT INTO `user` VALUES (5, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJPYdBibcHGd3yHTa1CEVIic5cibibZX0V30BB9iaGv4UtWxllVqeDqFyOmxyhlg0JHf4CHENVgRLCRsZQ/132', 1, '山东', '菏泽', 0, 1);
INSERT INTO `user` VALUES (6, 'oaRAQ4-F3YaJ8eCmJli5VJdHVoak', '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 1);
INSERT INTO `user` VALUES (7, NULL, 'RAJA', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJiaMiaT5raXFHHW6aKUCvo1LoNOWs4BZmmAhSlWIhiahsKHhDR2b7MIz5wLibwGr222eOPpRjFHn7wtg/132', 1, '江苏', '盐城', 0, 0);
INSERT INTO `user` VALUES (8, NULL, 'Serendipity', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLKXTwicnjZL7Ouw7cbNghND9UcOz7h5wt9WZsf7WKGpIB918ejick0EzwAIiabiazPkJa7OMUkicviaZhA/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (20, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (21, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (22, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (23, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (24, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (25, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (26, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (27, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (28, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (29, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (30, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (31, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (32, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (33, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (34, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (35, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (36, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (37, 'oMUSN5LTfmqPkdSmygz2BY6iBtu8', 'SAILING', 'https://thirdwx.qlogo.cn/mmopen/vi_32/yytxNunwKBAoLWPQK2XPpribXHia1iaHEgHffE4GSiaPic4LV6ib1ap3va68BSBs8JdU2HfQpe6657aWtiavm7mHoyVbg/132', 1, '山东', '聊城', 0, 0);
INSERT INTO `user` VALUES (38, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (39, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (40, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (41, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (42, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (43, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (44, NULL, 'SAILING', 'https://thirdwx.qlogo.cn/mmopen/vi_32/ABic4K4xf19bKibiaIDdSD7Y6uJDEVzVd5icJSIkicqhZ82FPpG2ibjguKfRgWPKRHITZoKtjLMwOwicHxk68EuCV7cQg/132', 1, '山东', '聊城', 0, 0);
INSERT INTO `user` VALUES (45, NULL, '苏杭', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJS28gq6epfzhGSsbpGEzJ1EvJ2eGlWs96Dun7jPYrlZBkWIcQAg79iakrSR9jD0K91ezmW8E6yO9Q/132', 1, '', '', 0, 0);
INSERT INTO `user` VALUES (46, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (47, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (48, NULL, 'SAILING', 'https://thirdwx.qlogo.cn/mmopen/vi_32/ABic4K4xf19bKibiaIDdSD7Y6uJDEVzVd5icJSIkicqhZ82FPpG2ibjguKfRgWPKRHITZoKtjLMwOwicHxk68EuCV7cQg/132', 1, '山东', '聊城', 0, 0);
INSERT INTO `user` VALUES (49, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (50, NULL, 'SAILING', 'https://thirdwx.qlogo.cn/mmopen/vi_32/ABic4K4xf19bKibiaIDdSD7Y6uJDEVzVd5icJSIkicqhZ82FPpG2ibjguKfRgWPKRHITZoKtjLMwOwicHxk68EuCV7cQg/132', 1, '山东', '聊城', 0, 0);
INSERT INTO `user` VALUES (51, NULL, '🐯肖 军🐔', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLtVyohxAYEqmZt1n4YiblwNZpJNZKZgC3EYlqY0IqSqbiaUj9hPk8PicTGiaIqfGw3TkcVWFib4sbicqfQ/132', 1, '上海', '松江', 0, 0);
INSERT INTO `user` VALUES (52, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (53, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (54, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (55, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 0);
INSERT INTO `user` VALUES (56, NULL, '1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI9za8CkrUfOjCn1Se9URSibCWpmMiapibhmqToEjhMTXFMDq0FShcd4r3XupMlSV2PmTrUAK2g2hVjA/132', 1, '山东', '菏泽', 0, 1);
INSERT INTO `user` VALUES (57, NULL, '戊六七', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXtSSl1CJPz4svc5KZYJYzXBCvcCBfKicqnjveL5gPPVYTky7iacGX68NM97cEoh0iasbdicWoaTZj1w/132', 1, '安徽', '安庆', 0, 0);
INSERT INTO `user` VALUES (58, NULL, '戊六七', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXtSSl1CJPz4svc5KZYJYzXBCvcCBfKicqnjveL5gPPVYTky7iacGX68NM97cEoh0iasbdicWoaTZj1w/132', 1, '安徽', '安庆', 0, 0);
INSERT INTO `user` VALUES (59, NULL, 'M', 'https://thirdwx.qlogo.cn/mmopen/vi_32/77WH4bUqSbhaHRYfo4x0XLR0qwawbu21mjLjVXfo0IDu4sxJy44PfMeLro4dMyoITDxia85NfFyjRuLiaMdVWQWQ/132', 2, '', '', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
