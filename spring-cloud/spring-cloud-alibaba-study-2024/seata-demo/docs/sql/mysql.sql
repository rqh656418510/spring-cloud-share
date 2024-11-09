-- 基于 Seata Server 2.2.0

-- -------------------- Seata 库 --------------------------------
CREATE DATABASE IF NOT EXISTS `seata` default character set utf8mb4;

USE `seata`;

CREATE TABLE IF NOT EXISTS `global_table` (
	`xid` VARCHAR(128) NOT NULL,
	`transaction_id` BIGINT,
	`status` TINYINT NOT NULL,
	`application_id` VARCHAR(32),
	`transaction_service_group` VARCHAR(32),
	`transaction_name` VARCHAR(128),
	`timeout` INT,
	`begin_time` BIGINT,
	`application_data` VARCHAR(2000),
	`gmt_create` DATETIME,
	`gmt_modified` DATETIME,
	PRIMARY KEY (`xid`),
	KEY `idx_status_gmt_modified` (`status`, `gmt_modified`),
	KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `branch_table` (
	`branch_id` BIGINT NOT NULL,
	`xid` VARCHAR(128) NOT NULL,
	`transaction_id` BIGINT,
	`resource_group_id` VARCHAR(32),
	`resource_id` VARCHAR(256),
	`branch_type` VARCHAR(8),
	`status` TINYINT,
	`client_id` VARCHAR(64),
	`application_data` VARCHAR(2000),
	`gmt_create` DATETIME(6),
	`gmt_modified` DATETIME(6),
	PRIMARY KEY (`branch_id`),
	KEY `idx_xid` (`xid`)
) ENGINE = InnoDB CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `lock_table` (
	`row_key` VARCHAR(128) NOT NULL,
	`xid` VARCHAR(128),
	`transaction_id` BIGINT,
	`branch_id` BIGINT NOT NULL,
	`resource_id` VARCHAR(256),
	`table_name` VARCHAR(32),
	`pk` VARCHAR(36),
	`status` TINYINT NOT NULL DEFAULT '0' COMMENT '0:locked ,1:rollbacking',
	`gmt_create` DATETIME,
	`gmt_modified` DATETIME,
	PRIMARY KEY (`row_key`),
	KEY `idx_status` (`status`),
	KEY `idx_branch_id` (`branch_id`),
	KEY `idx_xid` (`xid`)
) ENGINE = InnoDB CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `distributed_lock` (
	`lock_key` CHAR(20) NOT NULL,
	`lock_value` VARCHAR(20) NOT NULL,
	`expire` BIGINT,
	PRIMARY KEY (`lock_key`)
) ENGINE = InnoDB CHARSET = utf8mb4;

INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('AsyncCommitting', ' ', 0);

INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('RetryCommitting', ' ', 0);

INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('RetryRollbacking', ' ', 0);

INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('TxTimeoutCheck', ' ', 0);

CREATE TABLE IF NOT EXISTS `vgroup_table` (
	`vGroup` VARCHAR(255),
	`namespace` VARCHAR(255),
	`cluster` VARCHAR(255),
	UNIQUE `idx_vgroup_namespace_cluster` (`vGroup`, `namespace`, `cluster`)
) ENGINE = InnoDB CHARSET = utf8mb4;

-- -------------------------------- 账户库 --------------------------------

CREATE DATABASE IF NOT EXISTS `seata_account` default character set utf8mb4;

USE `seata_account`;

CREATE TABLE IF NOT EXISTS `t_account` (
	`id` BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
	`user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
	`total` DECIMAL(18, 5) DEFAULT NULL COMMENT '总额度',
	`used` DECIMAL(18, 5) DEFAULT NULL COMMENT '已用账户余额',
	`residue` DECIMAL(18, 5) DEFAULT '0' COMMENT '剩余可用额度'
) ENGINE = INNODB AUTO_INCREMENT = 2 CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';

ALTER TABLE `undo_log` ADD INDEX `ix_log_created` (`log_created`);

INSERT INTO t_account(`id`,`user_id`,`total`,`used`,`residue`)VALUES('1','1','1000','0','1000');

-- -------------------------------- 库存库 --------------------------------

CREATE DATABASE IF NOT EXISTS `seata_storage` default character set utf8mb4;

USE `seata_storage`;

CREATE TABLE IF NOT EXISTS `t_storage` (
	`id` BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
	`total` INT(11) DEFAULT NULL COMMENT '总库存',
	`used` INT(11) DEFAULT NULL COMMENT '已用库存',
	`residue` INT(11) DEFAULT NULL COMMENT '剩余库存'
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';

ALTER TABLE `undo_log` ADD INDEX `ix_log_created` (`log_created`);

INSERT INTO t_storage(`id`,`product_id`,`total`,`used`,`residue`)VALUES('1','1','100','0','100');

-- -------------------------------- 订单库 --------------------------------

CREATE DATABASE IF NOT EXISTS `seata_order` default character set utf8mb4;

USE `seata_order`;

CREATE TABLE IF NOT EXISTS t_order (
	`id` BIGINT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
	`product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
	`count` INT(11) DEFAULT NULL COMMENT '数量',
	`money` DECIMAL(18, 5) DEFAULT NULL COMMENT '金额',
	`status` INT(1) DEFAULT NULL COMMENT '订单状态: 0:创建中; 1:已完结'
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';

ALTER TABLE `undo_log` ADD INDEX `ix_log_created` (`log_created`);

