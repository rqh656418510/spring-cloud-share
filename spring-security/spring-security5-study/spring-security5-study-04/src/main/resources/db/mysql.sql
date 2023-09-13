-- 创建数据库
CREATE DATABASE `spring_security_study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 切换数据库
USE `spring_security_study`;

-- 创建用户表
create table user(
    id bigint primary key auto_increment,
    username varchar(20) unique not null,
    password varchar(100)
);

-- 插入初始化数据（用户密码是123456）
insert into user values(1, 'wangwu', '$2a$10$IwvZiSm3vdhRtdyU8rJQz.pb9U/kYHorC2aQqwtFX.RVuFFHOpt82');
insert into user values(2, 'zhangsan', '$2a$10$IwvZiSm3vdhRtdyU8rJQz.pb9U/kYHorC2aQqwtFX.RVuFFHOpt82');
