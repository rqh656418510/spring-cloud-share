CREATE DATABASE `spring_cloud_2024` default character set utf8mb4 collate utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `t_pay`;
CREATE TABLE `t_pay` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `pay_no` VARCHAR(50) NOT NULL COMMENT '支付流水号',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单流水号',
    `user_id` INT(10) DEFAULT '1' COMMENT '用户账号ID',
    `amount` DECIMAL(8, 2) NOT NULL DEFAULT '9.9' COMMENT '交易金额',
    `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '支付交易表';

INSERT INTO t_pay(pay_no,order_no) VALUES('17203699', '6544bafb424a');
