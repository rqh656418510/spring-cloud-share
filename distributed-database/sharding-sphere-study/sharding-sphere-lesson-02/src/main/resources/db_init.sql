-- 在用户数据库服务器（server-user）中，执行以下 SQL 语句：

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

-- 在订单数据库服务器（server-order）中，执行以下 SQL 语句：

-- 创建数据库
CREATE DATABASE db_order;

-- 切换数据库
USE db_order;

-- 创建表
CREATE TABLE t_order (
  id BIGINT AUTO_INCREMENT,
  order_no VARCHAR(30),
  user_id BIGINT,
  amount DECIMAL(10,2),
  PRIMARY KEY(id) 
);
