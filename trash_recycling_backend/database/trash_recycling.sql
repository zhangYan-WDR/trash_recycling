/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : trash_recycling

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 18/04/2023 17:00:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for td_address
-- ----------------------------
DROP TABLE IF EXISTS `td_address`;
CREATE TABLE `td_address`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户电话',
  `location_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '定位地址',
  `detailed_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `is_default` tinyint NULL DEFAULT NULL COMMENT '是否默认地址',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_address
-- ----------------------------

-- ----------------------------
-- Table structure for td_category
-- ----------------------------
DROP TABLE IF EXISTS `td_category`;
CREATE TABLE `td_category`  (
  `id` int NOT NULL COMMENT 'id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类别名称',
  `category_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类别规则',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_category
-- ----------------------------
INSERT INTO `td_category` VALUES (1, '可回收物', '可回收物指适宜回收利用和资源化利用的生活废弃物。可回收物主要包括：废纸、废弃塑料瓶、废金属、废包装物、废旧纺织物、废弃电器电子产品、废玻璃、废纸塑铝复合包装等。');
INSERT INTO `td_category` VALUES (2, '厨余垃圾', '厨余垃圾是指居民日常生活及食品加工、饮食服务、单位供餐等活动中产生的垃圾，包括丢弃不用的菜叶、剩菜、剩饭、果皮、蛋壳、茶渣、骨头等，其主要来源为家庭厨房、餐厅、饭店、食堂、市场及其他与食品加工有关的行业。');
INSERT INTO `td_category` VALUES (3, '有害垃圾', '有害垃圾指对人体健康或者自然环境造成直接或者潜在危害生活的废弃物。常见的有害垃圾包括废灯管、废油漆、杀虫剂、废弃化妆品、过期药品、废电池、废灯泡、废水银温度计等，有害垃圾需按照特殊正确的方法安全处理。');
INSERT INTO `td_category` VALUES (4, '其他垃圾', '其他垃圾指危害比较小，没有再次利用的价值的垃圾，如建筑垃圾，生活垃圾等，一般都采取填埋、焚烧、卫生分解等方法处理，部分还可以使用生物分解的方法解决，如放蚯蚓等。其他垃圾是可回收物、厨余垃圾、有害垃圾剩余下来的一种垃圾种类。');

-- ----------------------------
-- Table structure for td_category_detail
-- ----------------------------
DROP TABLE IF EXISTS `td_category_detail`;
CREATE TABLE `td_category_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` int NULL DEFAULT NULL COMMENT '垃圾类别',
  `detail_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细名称',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_category_detail
-- ----------------------------
INSERT INTO `td_category_detail` VALUES (1, 1, '不锈钢盆', '可回收物');
INSERT INTO `td_category_detail` VALUES (2, 1, '婴儿手推车', '可回收物');
INSERT INTO `td_category_detail` VALUES (3, 1, '遮阳伞', '可回收物');
INSERT INTO `td_category_detail` VALUES (4, 1, '滴露消毒液瓶', '可回收物');
INSERT INTO `td_category_detail` VALUES (5, 1, '老虎钳', '可回收物');
INSERT INTO `td_category_detail` VALUES (6, 1, '老虎夹', '可回收物');
INSERT INTO `td_category_detail` VALUES (7, 2, '草鱼', '厨余垃圾');
INSERT INTO `td_category_detail` VALUES (8, 2, '兔子骨头', '厨余垃圾');
INSERT INTO `td_category_detail` VALUES (9, 2, '酱油饭', '厨余垃圾');
INSERT INTO `td_category_detail` VALUES (10, 2, '乌贼', '厨余垃圾');
INSERT INTO `td_category_detail` VALUES (11, 2, '罗汉果', '厨余垃圾');
INSERT INTO `td_category_detail` VALUES (12, 2, '金枪鱼刺身', '厨余垃圾');
INSERT INTO `td_category_detail` VALUES (13, 3, 'LED灯（含水银）', '有害垃圾');
INSERT INTO `td_category_detail` VALUES (14, 3, '手术残余物', '有害垃圾');
INSERT INTO `td_category_detail` VALUES (15, 3, '万能表电池', '有害垃圾');
INSERT INTO `td_category_detail` VALUES (16, 3, '火漆', '有害垃圾');
INSERT INTO `td_category_detail` VALUES (17, 3, '空涂料桶', '有害垃圾');
INSERT INTO `td_category_detail` VALUES (18, 3, '药品包装瓶', '有害垃圾');
INSERT INTO `td_category_detail` VALUES (19, 4, '玻璃钢', '其他垃圾');
INSERT INTO `td_category_detail` VALUES (20, 4, '塑料绳', '其他垃圾');
INSERT INTO `td_category_detail` VALUES (21, 4, '石灰石', '其他垃圾');
INSERT INTO `td_category_detail` VALUES (22, 4, '蜡油', '其他垃圾');
INSERT INTO `td_category_detail` VALUES (23, 4, '毽子', '其他垃圾');
INSERT INTO `td_category_detail` VALUES (24, 4, '跳绳', '其他垃圾');

-- ----------------------------
-- Table structure for td_exam
-- ----------------------------
DROP TABLE IF EXISTS `td_exam`;
CREATE TABLE `td_exam`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `exam_record_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试记录编号',
  `score` int NULL DEFAULT NULL COMMENT '分数',
  `test_success_num` int NULL DEFAULT NULL COMMENT '成功数量',
  `test_wrong_num` int NULL DEFAULT NULL COMMENT '失败数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '考试时间',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_exam
-- ----------------------------

-- ----------------------------
-- Table structure for td_integral
-- ----------------------------
DROP TABLE IF EXISTS `td_integral`;
CREATE TABLE `td_integral`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `integral_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '积分编号',
  `integral_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '积分标题',
  `integral_update` int NULL DEFAULT NULL COMMENT '积分变动',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_add` tinyint NULL DEFAULT NULL COMMENT '是否增加',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_integral
-- ----------------------------

-- ----------------------------
-- Table structure for td_order
-- ----------------------------
DROP TABLE IF EXISTS `td_order`;
CREATE TABLE `td_order`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_price` int NULL DEFAULT NULL COMMENT '订单金额',
  `order_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单状态',
  `order_address_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单地址编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_order
-- ----------------------------

-- ----------------------------
-- Table structure for td_recovery
-- ----------------------------
DROP TABLE IF EXISTS `td_recovery`;
CREATE TABLE `td_recovery`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `recovery_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回收类别名称',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_recovery
-- ----------------------------

-- ----------------------------
-- Table structure for td_user
-- ----------------------------
DROP TABLE IF EXISTS `td_user`;
CREATE TABLE `td_user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户code',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `personal_points` int NULL DEFAULT NULL COMMENT '个人积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of td_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
