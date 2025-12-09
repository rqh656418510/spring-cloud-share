-- 在 MySQL 主库（`Master`）中，执行以下 SQL 语句：

-- 创建数据库
CREATE DATABASE db_user;

-- 切换数据库
USE db_user;

-- 创建表
CREATE TABLE t_user (
 id BIGINT AUTO_INCREMENT,
 uname VARCHAR(30),
 PRIMARY KEY (id)
);

-- 插入表数据
INSERT INTO t_user(uname) VALUES('zhang3');
INSERT INTO t_user(uname) VALUES(@@hostname);
