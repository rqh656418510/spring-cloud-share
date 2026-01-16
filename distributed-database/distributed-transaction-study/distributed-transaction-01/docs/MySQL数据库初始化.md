- 创建数据库

``` sql
-- 创建 TCC 数据库
CREATE DATABASE tcc DEFAULT CHARACTER SET utf8;

-- 创建扣钱数据库
CREATE DATABASE account_from DEFAULT CHARACTER SET utf8;

-- 创建加钱数据库
CREATE DATABASE account_to DEFAULT CHARACTER SET utf8;
```

- 创建数据库表（`account_from.t_account`）

``` sql
-- 切换数据库
USE account_from;

-- 创建数据库表
CREATE TABLE `t_account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
    `name` varchar(256) NOT NULL DEFAULT '' COMMENT '用户名称',
    `balance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
    PRIMARY KEY (`id`)
);

-- 插入数据
INSERT INTO account_from.t_account(id, name, balance) VALUES(1, '张三', 100);
```

- 创建数据库表（`account_to.t_account`）

``` sql
-- 切换数据库
USE account_to;

-- 创建数据库表
CREATE TABLE `t_account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
    `name` varchar(256) NOT NULL DEFAULT '' COMMENT '用户名称',
    `balance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
    PRIMARY KEY (`id`)
);

-- 插入数据
INSERT INTO account_to.t_account(id, name, balance) VALUES(1, '李四', 100);
```
