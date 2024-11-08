/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : ordersystem

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 03/11/2024 23:10:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1、Table structure for o_category（3饮品分类表）
-- ----------------------------
DROP TABLE IF EXISTS `o_category`;
CREATE TABLE `o_category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Category ID',
  `shop_id` int NOT NULL COMMENT 'Shop ID, the category belongs to the shop',
  `name` varchar(20) NOT NULL COMMENT 'Category name',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `shop_id` FOREIGN KEY (`shop_id`) REFERENCES `o_shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;


-- ----------------------------
-- Records of o_category
-- ----------------------------
BEGIN;
INSERT INTO `o_category` VALUES (1, 1, 'Bun Category');
INSERT INTO `o_category` VALUES (2, 1, 'Delicious Steamed Dumplings');
INSERT INTO `o_category` VALUES (3, 1, 'Signature Rice Rolls');
INSERT INTO `o_category` VALUES (4, 1, 'Nutritious Porridge');
INSERT INTO `o_category` VALUES (5, 2, 'Stir-Fry Category');
INSERT INTO `o_category` VALUES (6, 2, 'Fried Rice Category');
INSERT INTO `o_category` VALUES (7, 2, 'Roasted Meat');
INSERT INTO `o_category` VALUES (8, 2, 'Fried Noodles Category');
INSERT INTO `o_category` VALUES (9, 2, 'Extra Portions');
INSERT INTO `o_category` VALUES (10, 2, 'Beverage');
INSERT INTO `o_category` VALUES (12, 1, 'Test Category');
INSERT INTO `o_category` VALUES (13, 3, 'May Milk Tea');
INSERT INTO `o_category` VALUES (14, 3, 'Not Bad');
INSERT INTO `o_category` VALUES (15, 2, 'Test');
COMMIT;


-- ----------------------------
-- 2、Table structure for o_comment（7评论表）
-- ----------------------------
DROP TABLE IF EXISTS `o_comment`;
CREATE TABLE `o_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL,
  `name` varchar(20) NOT NULL DEFAULT 'Anonymous User' COMMENT 'User's WeChat name, uploaded from the frontend',
  `content` varchar(255) NOT NULL COMMENT 'Comment content',
  `reply` varchar(255) DEFAULT NULL COMMENT 'Shop owner's reply content',
  `is_reply` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Whether the shop owner has replied, shows reply button in backend management',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Comment date',
  `reply_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Reply date',
  PRIMARY KEY (`id`),
  KEY `shop_id_comment` (`shop_id`),
  CONSTRAINT `shop_id_comment` FOREIGN KEY (`shop_id`) REFERENCES `o_shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;


-- ----------------------------
-- Records of o_comment
-- ----------------------------
BEGIN;
INSERT INTO `o_comment` VALUES (3, 1, 'User43685', 'No chopsticks were given. Am I supposed to eat with my hands?????', NULL, b'0', '2024-10-01 14:31:10', '2024-10-01 14:31:10');
INSERT INTO `o_comment` VALUES (4, 1, 'Mortis', 'These dumplings are undercooked', NULL, b'0', '2024-10-02 15:34:50', '2024-10-02 15:34:50');
INSERT INTO `o_comment` VALUES (5, 1, 'Four-Eyed Chicken', 'Very tasty, very fresh', NULL, b'0', '2024-10-02 15:36:03', '2024-10-02 15:36:03');
INSERT INTO `o_comment` VALUES (6, 2, 'Anonymous User', 'Too oily, the dish is full of oil underneath', 'Sorry, we will pay attention next time', b'1', '2024-10-03 16:10:02', '2024-10-03 16:10:05');
INSERT INTO `o_comment` VALUES (7, 2, 'Diga Ultraman', 'It’s way too spicy. Did you get your chili for free?? Why use so much?', 'Next time, you can call and have it remade instead of leaving such a casual comment. Running a business is hard.', b'1', '2024-10-03 16:10:33', '2024-10-04 11:47:29');
INSERT INTO `o_comment` VALUES (8, 2, 'White Lotus', 'The taste is okay, but the portion is too small, hardly any meat', 'You only paid less than ten yuan, and after costs, I get just a few. How much meat do you expect???', b'1', '2024-10-03 16:11:05', '2024-10-04 11:46:49');
INSERT INTO `o_comment` VALUES (9, 2, 'Keyboard Warrior', 'Expensive and small portions, just close your shop already', 'University students these days really lack manners', b'1', '2024-10-03 16:50:35', '2024-10-04 11:37:00');
INSERT INTO `o_comment` VALUES (11, 1, 'Pringles', 'The buns are tiny, yet so expensive', NULL, b'0', '2024-10-04 11:49:30', NULL);
INSERT INTO `o_comment` VALUES (12, 1, 'Ballpoint Pen', 'The rice rolls are awful', NULL, b'0', '2024-10-04 11:50:28', NULL);
INSERT INTO `o_comment` VALUES (13, 2, 'Universal Ointment', 'Second time no utensils were given, what’s your problem?', NULL, b'0', '2024-10-04 11:51:22', NULL);
INSERT INTO `o_comment` VALUES (14, 2, 'Cup Lid', 'This barbecued pork rice is mostly fat, threw it all away, absolutely disgusting', NULL, b'0', '2024-10-04 11:52:03', NULL);
INSERT INTO `o_comment` VALUES (15, 1, 'Lip Balm', 'Never had such awful rice rolls, is the owner from Chaoshan?', NULL, b'0', '2024-10-04 11:54:11', NULL);
INSERT INTO `o_comment` VALUES (16, 1, 'Charger', 'The fried dough sticks smell bad, the oil is not fresh', NULL, b'0', '2024-10-04 11:54:45', NULL);
INSERT INTO `o_comment` VALUES (17, 1, 'Righteous Male Student', 'Delicious, delicious, I have to order dumplings every day', NULL, b'0', '2024-10-04 11:55:19', NULL);
INSERT INTO `o_comment` VALUES (18, 1, 'Dark Fairy Xiaoyue', 'Speechless, the shrimp in this fresh shrimp rice roll is rotten, got diarrhea after eating, lost all my fairy energy, avoiding this shop', NULL, b'0', '2024-10-04 11:56:52', NULL);
INSERT INTO `o_comment` VALUES (19, 1, 'Quibbler', 'Genuine review test, delicious', NULL, b'0', '2024-10-05 18:00:39', NULL);
INSERT INTO `o_comment` VALUES (20, 1, 'Quibbler', '', NULL, b'0', '2024-10-05 18:27:13', NULL);
INSERT INTO `o_comment` VALUES (21, 1, 'Quibbler', 'Never dodging taxes again', NULL, b'0', '2024-10-05 16:15:36', NULL);
INSERT INTO `o_comment` VALUES (22, 1, 'Quibbler', 'Good', NULL, b'0', '2024-10-05 16:20:35', NULL);
INSERT INTO `o_comment` VALUES (23, 2, 'Quibbler', 'comment test', 'thanks', b'1', '2024-10-05 19:36:48', '2024-10-05 19:37:26');
INSERT INTO `o_comment` VALUES (24, 1, 'Quibbler', 'So bad! Never ordering again!', NULL, b'0', '2024-10-06 14:58:58', NULL);
INSERT INTO `o_comment` VALUES (25, 3, 'Quibbler', 'Thesis defense, good luck', 'Got it!', b'1', '2024-10-06 15:57:25', '2024-10-06 15:59:38');
INSERT INTO `o_comment` VALUES (26, 1, 'Quibbler', 'Okay, not bad', NULL, b'0', '2024-10-06 16:11:55', NULL);
INSERT INTO `o_comment` VALUES (27, 3, 'Shijiu', 'Milk tea is good', NULL, b'0', '2024-10-06 16:21:15', NULL);
INSERT INTO `o_comment` VALUES (28, 3, 'Shijiu', 'Very good, very good', 'Thank you', b'1', '2024-10-06 16:26:48', '2024-10-06 16:28:17');
COMMIT;


-- ----------------------------
-- 3、Table structure for o_food（1饮品表）
-- ----------------------------
DROP TABLE IF EXISTS `o_food`;
CREATE TABLE `o_food` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Food item ID',
  `shop_id` int NOT NULL COMMENT 'Shop ID, indicating which shop the food item belongs to',
  `category_id` int NOT NULL COMMENT 'Category ID, indicating the classification of the food item',
  `name` varchar(20) NOT NULL COMMENT 'Name of the food item',
  `price` decimal(10,2) NOT NULL DEFAULT '15.00' COMMENT 'Price of the food item, rounded to two decimal places',
  `is_recommend` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Whether the food item is recommended today',
  `img_src` varchar(255) DEFAULT NULL COMMENT 'URL of the food item image',
  `description` varchar(255) DEFAULT 'No food description available' COMMENT 'Description of the food item, such as preparation method, ingredients, etc.',
  PRIMARY KEY (`id`),
  KEY `shop_id_for_food` (`shop_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `o_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shop_id_for_food` FOREIGN KEY (`shop_id`) REFERENCES `o_shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;


-- ----------------------------
-- Records of o_food
-- ----------------------------
BEGIN;
INSERT INTO `o_food` VALUES (1, 1, 1, '豆沙包(3个)', 5.99, b'0', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/豆沙包.png', '美味豆沙包222');
INSERT INTO `o_food` VALUES (2, 1, 1, '莲蓉包(2个)', 3.50, b'0', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/莲蓉包.png', '新鲜莲蓉包');
COMMIT;

-- ----------------------------
-- 4、Table structure for o_order（5订单表）
-- ----------------------------
DROP TABLE IF EXISTS `o_order`;
CREATE TABLE `o_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` int DEFAULT '0',
  `app_delete` bit(1) DEFAULT b'0',
  `admin_delete` bit(1) DEFAULT b'0',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `commented` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of o_order
-- ----------------------------
BEGIN;
INSERT INTO `o_order` VALUES (1, 1, 'testxyz14hgenu', 'Zhang San', 'Building 11, Life Area, Guangdong Pharmaceutical University, Xiaogu Wei, Panyu District, Guangzhou, Guangdong Province', '13538445129', 2, b'0', b'0', '2024-10-20 20:42:37', '2024-10-18 21:20:29', b'0');
INSERT INTO `o_order` VALUES (2, 1, 'yuhrfedhmedfhrfgh', 'Li Si', 'Building B, Teaching Area, Guangdong Pharmaceutical University, Xiaogu Wei Street, Guangzhou, Guangdong Province', '13538445129', 2, b'0', b'0', '2024-10-20 20:42:52', '2024-10-18 21:20:29', b'0');
INSERT INTO `o_order` VALUES (3, 1, 'sthkjikolfdvbh', 'Righteous Male Student', 'Building 10, Life Area, Guangdong Pharmaceutical University, Xiaogu Wei, Guangzhou, Guangdong Province', '13538445129', 2, b'0', b'0', '2024-10-20 20:43:12', '2024-10-18 21:20:29', b'0');
INSERT INTO `o_order` VALUES (4, 1, 'rwg6huijh97dxv', 'Keyboard Warrior', 'Building 1, Guangdong Pharmaceutical University, University Town, Guangzhou, Guangdong Province', '13638445990', 3, b'0', b'0', '2024-10-20 20:43:23', '2024-10-23 20:41:32', b'0');
COMMIT;

-- ----------------------------
-- 5、Table structure for o_order_food（6订单饮品表）
-- ----------------------------
DROP TABLE IF EXISTS `o_order_food`;
CREATE TABLE `o_order_food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `food_id` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of o_order_food
-- ----------------------------
BEGIN;
INSERT INTO `o_order_food` VALUES (1, 1, 1, 2);
INSERT INTO `o_order_food` VALUES (2, 1, 9, 1);
INSERT INTO `o_order_food` VALUES (3, 1, 17, 2);
INSERT INTO `o_order_food` VALUES (4, 2, 5, 1);
INSERT INTO `o_order_food` VALUES (5, 2, 6, 1);
INSERT INTO `o_order_food` VALUES (6, 3, 18, 1);
INSERT INTO `o_order_food` VALUES (7, 4, 12, 1);
INSERT INTO `o_order_food` VALUES (8, 9, 3, 1);
INSERT INTO `o_order_food` VALUES (9, 9, 5, 1);
INSERT INTO `o_order_food` VALUES (10, 10, 24, 1);
INSERT INTO `o_order_food` VALUES (11, 10, 31, 1);
INSERT INTO `o_order_food` VALUES (12, 11, 47, 1);
INSERT INTO `o_order_food` VALUES (13, 12, 50, 1);
INSERT INTO `o_order_food` VALUES (14, 13, 50, 1);
INSERT INTO `o_order_food` VALUES (15, 14, 29, 1);
INSERT INTO `o_order_food` VALUES (16, 15, 50, 1);
INSERT INTO `o_order_food` VALUES (17, 16, 29, 1);
INSERT INTO `o_order_food` VALUES (18, 17, 3, 1);
INSERT INTO `o_order_food` VALUES (19, 18, 33, 1);
INSERT INTO `o_order_food` VALUES (20, 19, 41, 1);
INSERT INTO `o_order_food` VALUES (21, 19, 39, 1);
INSERT INTO `o_order_food` VALUES (22, 19, 26, 1);
INSERT INTO `o_order_food` VALUES (23, 20, 8, 1);
INSERT INTO `o_order_food` VALUES (24, 21, 3, 1);
INSERT INTO `o_order_food` VALUES (25, 22, 15, 1);
INSERT INTO `o_order_food` VALUES (26, 23, 40, 2);
INSERT INTO `o_order_food` VALUES (27, 23, 41, 1);
INSERT INTO `o_order_food` VALUES (28, 23, 42, 1);
INSERT INTO `o_order_food` VALUES (29, 24, 47, 1);
INSERT INTO `o_order_food` VALUES (30, 24, 8, 1);
INSERT INTO `o_order_food` VALUES (31, 24, 11, 1);
INSERT INTO `o_order_food` VALUES (32, 24, 20, 1);
INSERT INTO `o_order_food` VALUES (33, 25, 1, 1);
INSERT INTO `o_order_food` VALUES (34, 25, 3, 1);
INSERT INTO `o_order_food` VALUES (35, 26, 25, 1);
INSERT INTO `o_order_food` VALUES (36, 27, 51, 1);
INSERT INTO `o_order_food` VALUES (37, 28, 51, 1);
INSERT INTO `o_order_food` VALUES (38, 29, 8, 1);
INSERT INTO `o_order_food` VALUES (39, 30, 21, 2);
INSERT INTO `o_order_food` VALUES (40, 30, 23, 1);
INSERT INTO `o_order_food` VALUES (41, 31, 21, 1);
INSERT INTO `o_order_food` VALUES (42, 31, 22, 1);
INSERT INTO `o_order_food` VALUES (43, 31, 23, 1);
INSERT INTO `o_order_food` VALUES (44, 31, 24, 1);
INSERT INTO `o_order_food` VALUES (45, 32, 22, 1);
INSERT INTO `o_order_food` VALUES (46, 32, 24, 1);
INSERT INTO `o_order_food` VALUES (47, 33, 52, 1);
INSERT INTO `o_order_food` VALUES (48, 34, 21, 1);
COMMIT;

-- ----------------------------
-- 6、Table structure for o_shop（2商家表）
-- ----------------------------
DROP TABLE IF EXISTS `o_shop`;
CREATE TABLE `o_shop` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Shop ID',
  `user_id` int NOT NULL COMMENT 'Owner ID, the shop belongs to the owner',
  `owner_name` varchar(20) DEFAULT NULL COMMENT 'Owner name',
  `shop_name` varchar(20) DEFAULT NULL COMMENT 'Shop name',
  `shop_src` varchar(255) DEFAULT NULL COMMENT 'Shop photo URL',
  `phone` varchar(20) DEFAULT NULL COMMENT 'Shop phone, same as owner phone',
  `summary` varchar(255) DEFAULT 'No description available for this shop~' COMMENT 'Shop description',
  `stars` int NOT NULL DEFAULT '5' COMMENT 'Shop rating, integer from 1 to 5',
  `weight` int NOT NULL DEFAULT '1' COMMENT 'Shop weight, integer from 1 to 10',
  `closed` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Whether the shop is open, modified by the owner, default is 0',
  `is_shut_down` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Whether the shop is listed, modified by the admin, default is 0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `o_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of o_shop
-- ----------------------------
BEGIN;
INSERT INTO `o_shop` VALUES (1, 2, 'Zhang Da Ma', 'Zhang Da Ma Breakfast Shop', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/screenshot-2024-10.png', '19857560888', 'Affordable breakfast, freshly made, order now! If there are any issues, please contact the phone number.', 2, 9, b'0', b'0');
INSERT INTO `o_shop` VALUES (2, 3, 'Li Da Ye', 'Unique Stir-Fry', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/screenshot-2024-10.png', '13538445567', 'There is always a stir-fry dish for you~', 2, 9, b'0', b'0');
INSERT INTO `o_shop` VALUES (3, 4, 'Li Milk Tea', 'Li Fat Pig Milk Tea', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/screenshot-2024-10.png', '19124338888', 'Delicious milk tea, gain a pound with every cup.', 2, 2, b'0', b'0');
INSERT INTO `o_shop` VALUES (4, 6, 'Wang Fried Chicken', 'Handmade Fried Chicken (University Town)', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/screenshot-2024-10.png', '19859509534', 'Handmade fried chicken, freshly cooked with aromatic recycled oil.', 2, 7, b'0', b'0');
INSERT INTO `o_shop` VALUES (5, 7, 'Cheng Barbecue', 'Bad Brothers Barbecue', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/screenshot-2024-10.png', '17738445990', 'Warm-hearted barbecue, cooked to order, no upset stomach or your money back.', 2, 8, b'0', b'0');
INSERT INTO `o_shop` VALUES (6, 8, 'Cup', 'Cup', 'http://food-1309527051.cos.ap-guangzhou.myqcloud.com/white-congee.png', '13834556880', 'No description available for this shop~', 5, 6, b'0', b'0');
COMMIT;

-- ----------------------------
-- Table structure for o_user（4用户表）
-- ----------------------------
DROP TABLE IF EXISTS `o_user`;
CREATE TABLE `o_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Owner\ 's ID',
  `username` varchar(255) NOT NULL COMMENT 'Owner login account',
  `password` varchar(255) NOT NULL COMMENT 'Owner login password',
  `is_admin` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Is the user an admin? 0 means owner',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of o_user
-- ----------------------------
BEGIN;
INSERT INTO `o_user` VALUES (1, 'admin', 'admin', b'1');
INSERT INTO `o_user` VALUES (2, 'zaocan', 'zaocan', b'0');
INSERT INTO `o_user` VALUES (3, 'wucan', 'wucan', b'0');
INSERT INTO `o_user` VALUES (4, 'naicha', 'naicha', b'0');
INSERT INTO `o_user` VALUES (5, 'drinkt', 'drinkt', b'0');
INSERT INTO `o_user` VALUES (6, 'zhaji', 'zhaji', b'0');
INSERT INTO `o_user` VALUES (7, 'shaokao', 'shaokao', b'0');
INSERT INTO `o_user` VALUES (8, 'lala', 'lala', b'0');
INSERT INTO `o_user` VALUES (9, '123', '123', b'0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
