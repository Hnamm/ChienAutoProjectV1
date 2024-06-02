-- -----------------------------------------------------
-- Schema full-stack-ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `full-stack-chienauto`;

CREATE SCHEMA `full-stack-chienauto`;
USE `full-stack-chienauto` ;

-- -----------------------------------------------------
-- Table `full-stack-ecommerce`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-chienauto`.`product_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `full-stack-ecommerce`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-chienauto`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(555) DEFAULT NULL,
  `unit_price` BIGINT(13) DEFAULT NULL,
  `image_url` VARCHAR(255) DEFAULT NULL,
  `active` BIT DEFAULT 1,
  `units_in_stock` INT(11) DEFAULT NULL,
  `max_speed` INT(10) DEFAULT NULL, 
  `road_one_time` INT(5) DEFAULT NULL,
  `battery_time` INT(5) DEFAULT NULL,
   `date_created` DATETIME(6) DEFAULT NULL,
  `last_updated` DATETIME(6) DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------

INSERT INTO product_category(category_name) VALUES ('Ô tô điện');
INSERT INTO product_category(category_name) VALUES ('Xe máy điện');

-- add data cars
INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('CAR-TECH-1000', 'VF5-PLUS',
 'VF5 Plus là mẫu xe ô tô điện cỡ nhỏ của VinFast, thiết kế hiện đại, tiện nghi và thân thiện với môi trường.
 Xe có khả năng vận hành mạnh mẽ, phù hợp cho đô thị và tiết kiệm năng lượng.',
'assets/images/products/vf-5.png'
,1,100, 150 , 205, 40, 500000000, 1, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('CAR-TECH-1001', 'VF6',
 'VF6 là mẫu SUV điện của VinFast với thiết kế thể thao, hiện đại và không gian nội thất rộng rãi.
 Xe tích hợp nhiều công nghệ thông minh và an toàn, mang lại trải nghiệm lái thoải mái và tiện nghi.',
'assets/images/products/vf-6.png'
,1,100, 160 ,210,45,645000000, 1, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('CAR-TECH-1002', 'VFe34',
'VF e34 là mẫu SUV điện đầu tiên của VinFast, thiết kế hiện đại và tiện nghi với không gian nội thất rộng rãi.
 Xe tích hợp nhiều công nghệ tiên tiến và hệ thống an toàn cao cấp, đảm bảo trải nghiệm lái xe thông minh và tiết kiệm năng lượng.',
'assets/images/products/vf-e34.png'
,1,100,120, 200, 55,450000000,1, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('CAR-TECH-1003', 'VF7',
'VinFast VF7 sẽ là một mẫu SUV hoặc crossover SUV với thiết kế hiện đại và tính năng tiện ích.
 Xe có thể được trang bị các công nghệ thông minh và hệ thống an toàn tiên tiến, mang lại trải nghiệm lái xe an toàn và thoải mái cho người dùng',
'assets/images/products/vf-7.png'
,1,100,170, 210, 45, 550000000, 1, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('CAR-TECH-1004', 'VF8',
 'VF8 là mẫu SUV điện của VinFast, mang thiết kế thời thượng và mạnh mẽ, phù hợp cho các gia đình hiện đại. 
 Xe được trang bị công nghệ thông minh và các tính năng an toàn tiên tiến, mang lại trải nghiệm lái xe tiện nghi và an toàn.',
'assets/images/products/vf-8.png'
,1,100,180,250,40,700000000,1, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('CAR-TECH-1005', 'VF9',
 'VF9 là mẫu SUV điện cao cấp của VinFast, sở hữu thiết kế sang trọng và hiện đại cùng không gian nội thất rộng rãi.
 Xe được trang bị các công nghệ tiên tiến và tính năng an toàn hàng đầu, mang đến trải nghiệm lái xe đẳng cấp và tiện nghi.',
'assets/images/products/vf-9.png'
,1,100,185, 255, 55, 735000000 , 1, NOW());

-- add data motors


INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('MOTOR-TECH-2000', 'VENTO S',
'Vento S có thể là một mẫu xe máy điện nhỏ gọn và tiện lợi, phù hợp với việc di chuyển trong đô thị.
 Xe có thể được thiết kế với tính năng hiện đại, tiện nghi và có thể điều khiển thông qua các công nghệ điện tử.',
'assets/images/products/vento-s.png'
,1,100,70, 90, 30, 12000000, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('MOTOR-TECH-2001', 'KLARA S(2022)',
'Klara S là một mẫu xe máy điện hoặc xe máy thông minh, có thể tích hợp các tính năng thông minh như kết nối điện thoại thông qua Bluetooth, hệ thống định vị GPS, và có thể điều khiển từ xa thông qua ứng dụng di động.
 Đặc biệt, xe có thể có tính năng an toàn như hệ thống phanh ABS và hệ thống kiểm soát trượt.',
'assets/images/products/klara-s(2022).png'
,1,100,75, 100, 32, 13500000, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('MOTOR-TECH-2002', 'THEON S',
'Theon S - Sự kết hợp hoàn hảo giữa công nghệ và tốc độ, mang đến trải nghiệm lái xe đỉnh cao,
 hiện đại, mạnh mẽ và đầy cá tính, là biểu tượng của sự sang trọng và tiên tiến trong làng siêu xe điện.',
'assets/images/products/theon-s.png'
,1,100,80, 100, 35, 15000000, 2, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
max_speed, road_one_time, battery_time, unit_price, category_id, date_created)
VALUES ('MOTOR-TECH-2003', 'FELIZ S',
'Feliz S là một mẫu xe điện cỡ nhỏ với thiết kế gọn gàng và tiện lợi, lý tưởng cho việc di chuyển trong thành phố.
 Với khả năng tăng tốc nhanh và khả năng di chuyển linh hoạt, Feliz S mang lại sự thoải mái và tiện nghi cho người dùng',
'assets/images/products/feliz-s.png'
,1,100,85, 110,40,14500000,2, NOW());

