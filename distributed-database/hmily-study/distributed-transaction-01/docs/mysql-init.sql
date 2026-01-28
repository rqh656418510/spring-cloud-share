-- 扣钱数据库

CREATE DATABASE `account_from` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE `account_from`;

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
    `name` varchar(256) NOT NULL DEFAULT '' COMMENT '用户名称',
    `balance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
    PRIMARY KEY (`id`)
);

INSERT INTO account_from.t_account(id, name, balance) VALUES(1, '张三', 100);

-- 加钱数据库

CREATE DATABASE `account_to` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE `account_to`;

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
    `name` varchar(256) NOT NULL DEFAULT '' COMMENT '用户名称',
    `balance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
    PRIMARY KEY (`id`)
);

INSERT INTO account_to.t_account(id, name, balance) VALUES(2, '李四', 100);
