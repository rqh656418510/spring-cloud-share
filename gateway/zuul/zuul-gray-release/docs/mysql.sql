-- 创建数据库
CREATE DATABASE zuul_gateway DEFAULT CHARACTER SET utf8mb4;

-- 创建网关灰度发布配置表
CREATE TABLE `gateway_gray_release_config` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `service_id` VARCHAR(255) DEFAULT NULL COMMENT '服务ID（注册中心中的服务名）',
  `path` VARCHAR(255) DEFAULT NULL COMMENT '路由路径（如 /api/order/get）',
  `enable_gray_release` TINYINT(11) DEFAULT NULL COMMENT '是否开启灰度发布（1-开启，0-关闭）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网关灰度发布配置表';

-- 创建网关API路由配置表
CREATE TABLE `gateway_api_route` (
  `id` VARCHAR(64) NOT NULL COMMENT '主键ID',
  `path` VARCHAR(255) NOT NULL COMMENT '路由路径，如 /api/order/get',
  `service_id` VARCHAR(128) DEFAULT NULL COMMENT '服务ID（注册中心中的服务名）',
  `url` VARCHAR(512) DEFAULT NULL COMMENT '直连URL（与service_id二选一）',
  `strip_prefix` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否去除前缀（1-是，0-否）',
  `retryable` TINYINT(1) DEFAULT NULL COMMENT '是否支持重试（1-是，0-否）',
  `enabled` TINYINT(1) DEFAULT 1 COMMENT '是否启用（1-启用，0-禁用）',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网关API路由配置表';

-- 插入测试数据
INSERT INTO zuul_gateway.gateway_api_route (id,`path`,service_id,url,strip_prefix,retryable,enabled,create_time,update_time) VALUES
('1','/inventory/**','inventory-service','/inventory-service/inventory',1,1,1,'2026-03-18 08:31:31.0','2026-03-18 08:46:14.0');

-- 插入测试数据
INSERT INTO zuul_gateway.gateway_gray_release_config (service_id,`path`,enable_gray_release) VALUES
('inventory-service','/inventory/deduct',1),
('inventory-service','/inventory/increase',0);
