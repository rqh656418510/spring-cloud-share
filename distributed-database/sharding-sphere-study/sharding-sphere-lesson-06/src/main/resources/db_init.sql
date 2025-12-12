-- 在订单数据库服务器一（server-order0）中，执行以下 SQL 语句：

-- 创建数据库
CREATE DATABASE db_order;

-- 切换数据库
USE db_order;

-- 创建表
CREATE TABLE t_dict(
    id BIGINT,
    dict_type VARCHAR(200),
    PRIMARY KEY(id)
);

-- 在订单数据库服务器二（server-order1）中，执行以下 SQL 语句：

-- 创建数据库
CREATE DATABASE db_order;

-- 切换数据库
USE db_order;

-- 创建表
CREATE TABLE t_dict(
    id BIGINT,
    dict_type VARCHAR(200),
    PRIMARY KEY(id)
);
