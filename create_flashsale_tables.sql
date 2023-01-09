
DROP DATABASE IF EXISTS `flashsale`;
CREATE DATABASE `flashsale`;
USE `flashsale`;

CREATE TABLE t_user(
	id VARCHAR(255) NOT NULL COMMENT 'userID',
	username VARCHAR(255) not NULL,
	`password` VARCHAR(32) DEFAULT NULL COMMENT 'double hashed, MD5(MD5(cleartext + static salt) + random salt)',
	salt VARCHAR(10) DEFAULT NULL,
	avatar VARCHAR(128) DEFAULT NULL,
	register_date datetime DEFAULT NULL,
	last_login_date datetime DEFAULT NULL,
	login_count int(11) DEFAULT '0',
	PRIMARY KEY(id)
)
COMMENT 'User Table';

INSERT INTO `flashsale`.`t_user` (`id`, `username`, `password`, `salt`) VALUES ('1@3.com', 'admin', 'db2a5b1685ebc5272feccbe98a20abca', 'randomsalt');

------------------------------------------------
CREATE TABLE t_product(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'productID',
	product_name VARCHAR(16) DEFAULT NULL COMMENT 'product name',
	product_title VARCHAR(64) DEFAULT NULL COMMENT 'product title',
	product_img VARCHAR(64) DEFAULT NULL COMMENT 'product image',
	product_detail LONGTEXT COMMENT 'product description',
	product_price DECIMAL(10,2) DEFAULT '0.00' COMMENT 'product price',
	product_stock INT(11) DEFAULT '0' COMMENT 'product stock, -1 indicating unlimited stock',
	PRIMARY KEY(id)
)
COMMENT 'Product Table';

INSERT INTO `flashsale`.`t_product` VALUES (1, 'Nintendo - White', 'Super Nintendo Entertainment System Controller - Milk White', '/img/1.jpg', 'For sale is a fully tested and working refurbished Nintendo SNES controller. It may have some scratches/wear due to age, but this doesn’t affect functionality in any way.', '99.99', 1000);
INSERT INTO `flashsale`.`t_product` VALUES (2, 'Nintendo - Gray', 'Super Nintendo Entertainment System Controller - Classic Gray', '/img/2.jpg', 'For sale is a fully tested and working refurbished Nintendo SNES controller. It may have some scratches/wear due to age, but this doesn’t affect functionality in any way.', '79.98', 1000);
INSERT INTO `flashsale`.`t_product` VALUES (3, 'Xbox - Black', 'Xbox Elite Wireless Controller Series 2 - Classy Black', '/img/3.jpg', 'The Xbox Elite Wireless Controller Series 2 features over 30 ways to play like a pro, including adjustable-tension thumbsticks, new interchangeable components, up to 40 hours of rechargeable battery life, and limitless customization with the Xbox Accessories app on Xbox and Windows.', '179.99', 1000);
INSERT INTO `flashsale`.`t_product` VALUES (4, 'Xbox - White', 'Xbox Elite Wireless Controller Series 2 - Stylish White', '/img/4.jpg', 'The Xbox Elite Wireless Controller Series 2 features over 30 ways to play like a pro, including adjustable-tension thumbsticks, new interchangeable components, up to 40 hours of rechargeable battery life, and limitless customization with the Xbox Accessories app on Xbox and Windows.', '199.99', 1000);
INSERT INTO `flashsale`.`t_product` VALUES (5, 'Xbox - Red', 'Xbox Elite Wireless Controller Series 2 - Popular Red', '/img/5.jpg', 'The Xbox Elite Wireless Controller Series 2 features over 30 ways to play like a pro, including adjustable-tension thumbsticks, new interchangeable components, up to 40 hours of rechargeable battery life, and limitless customization with the Xbox Accessories app on Xbox and Windows.', '209.99', 1000);

------------------------------------------------
CREATE TABLE `t_order` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'orderID',
	`user_id` VARCHAR(255) DEFAULT NULL COMMENT 'userID',
	`product_id` BIGINT(20) DEFAULT NULL COMMENT 'productID',
	`delivery_addr_id` BIGINT(20) DEFAULT NULL  COMMENT 'address ID',
	`product_name` VARCHAR(16) DEFAULT NULL  COMMENT 'product name',
	`product_count` INT(20) DEFAULT '0'  COMMENT '#product',
	`product_price` DECIMAL(10,2) DEFAULT '0.00'  COMMENT 'product price',
	`order_channel` TINYINT(4) DEFAULT '0'  COMMENT '1-pc, 2-android, 3-ios',
	`status` TINYINT(4) DEFAULT '0'  COMMENT 'order status，0-unpaied，1-paid，2-shipped，3-delivered，4-returned，5-completed',
	`create_date` datetime DEFAULT NULL,
	`payment_date` datetime DEFAULT NULL,
	PRIMARY KEY(`id`)
)
COMMENT 'Order Table';
------------------------------------------------
CREATE TABLE `t_flashsale_product`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'flash sale product ID',
	`product_id` BIGINT(20) NOT NULL,
	`flashsale_price` DECIMAL(10,2) NOT NULL,
	`stock_count` INT(10) NOT NULL,
	`start_date` datetime NOT NULL  COMMENT 'start time of flash sale',
	`end_date` datetime NOT NULL COMMENT 'end time of flash sale',
	PRIMARY KEY(`id`)
)
COMMENT 'Flash Sale Products';

INSERT INTO `flashsale`.`t_flashsale_product` VALUES (1, 1, '59.99', 100, '2023-01-04 21:00:00', '2023-01-05 19:00:00');
INSERT INTO `flashsale`.`t_flashsale_product` VALUES (2, 2, '35.50', 50, '2023-01-06 08:00:00', '2025-01-06 23:59:59');
INSERT INTO `flashsale`.`t_flashsale_product` VALUES (3, 3, '135.59', 100, '2025-01-06 08:00:00', '2025-01-06 23:00:00');
INSERT INTO `flashsale`.`t_flashsale_product` VALUES (4, 4, '159.99', 100, '2023-01-06 08:00:00', '2025-01-06 23:59:59');
INSERT INTO `flashsale`.`t_flashsale_product` VALUES (5, 5, '180.55', 100000, '2023-01-06 08:00:00', '2025-01-06 23:59:59');

------------------------------------------------
CREATE TABLE `t_flashsale_order` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'flash sale order ID',
	`user_id` VARCHAR(255) NOT NULL,
	`order_id` BIGINT(20) NOT NULL,
	`product_id` BIGINT(20) NOT NULL,
	PRIMARY KEY(`id`)
)
COMMENT 'Flash Sale Orders';