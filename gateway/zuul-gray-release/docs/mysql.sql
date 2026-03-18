-- 创建数据库
CREATE DATABASE zuul_gateway DEFAULT CHARACTER SET utf8;

-- 创建灰度发布配置表
CREATE TABLE `gray_release_config` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `service_id` varchar(255) DEFAULT NULL,
   `path` varchar(255) DEFAULT NULL,
   `enable_gray_release` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
