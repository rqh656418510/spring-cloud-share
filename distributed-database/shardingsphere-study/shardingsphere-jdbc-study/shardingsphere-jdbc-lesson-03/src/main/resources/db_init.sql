-- 在订单数据库服务器一（server-order0）中，执行以下 SQL 语句：

-- 创建数据库
CREATE DATABASE db_order;

-- 切换数据库
USE db_order;

-- 创建表（水平分库时不能使用数据库的自增主键，否则不同库不同表之间会发生主键冲突）
CREATE TABLE t_order0 (
  id BIGINT,
  order_no VARCHAR(30),
  user_id BIGINT,
  amount DECIMAL(10,2),
  PRIMARY KEY(id) 
);

-- 创建表（水平分库时不能使用数据库的自增主键，否则不同库不同表之间会发生主键冲突）
CREATE TABLE t_order1 (
  id BIGINT,
  order_no VARCHAR(30),
  user_id BIGINT,
  amount DECIMAL(10,2),
  PRIMARY KEY(id) 
);

-- 在订单数据库服务器二（server-order1）中，执行以下 SQL 语句：

-- 创建数据库
CREATE DATABASE db_order;

-- 切换数据库
USE db_order;

-- 创建表（水平分库时不能使用数据库的自增主键，否则不同库不同表之间会发生主键冲突）
CREATE TABLE t_order0 (
  id BIGINT,
  order_no VARCHAR(30),
  user_id BIGINT,
  amount DECIMAL(10,2),
  PRIMARY KEY(id) 
);

-- 创建表（水平分库时不能使用数据库的自增主键，否则不同库不同表之间会发生主键冲突）
CREATE TABLE t_order1 (
  id BIGINT,
  order_no VARCHAR(30),
  user_id BIGINT,
  amount DECIMAL(10,2),
  PRIMARY KEY(id) 
);

