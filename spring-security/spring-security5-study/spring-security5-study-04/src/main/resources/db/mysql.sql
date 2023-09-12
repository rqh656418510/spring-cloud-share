--- 创建数据库
CREATE DATABASE `spring_security_study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

--- 创建用户表
create table users(
    id bigint primary key auto_increment,
    username varchar(20) unique not null,
    password varchar(100)
);

--- 创建角色表
create table role(
    id bigint primary key auto_increment,
    name varchar(20)
);

--- 创建权限表
create table menu(
    id bigint primary key auto_increment,
    name varchar(20),
    url varchar(100),
    parent_id bigint,
    permission varchar(20)
);

--- 创建用户角色关联表
create table user_role(
    uid bigint,
    rid bigint
);

--- 创建角色权限关联表
create table role_menu(
    mid bigint,
    rid bigint
);

--- 插入初始化数据
insert into users values(1, 'wangwu', '$2a$10$IwvZiSm3vdhRtdyU8rJQz.pb9U/kYHorC2aQqwtFX.RVuFFHOpt82');
insert into users values(2, 'zhangsan', '$2a$10$IwvZiSm3vdhRtdyU8rJQz.pb9U/kYHorC2aQqwtFX.RVuFFHOpt82');
insert into menu values(1, '系统管理', '', 0, 'menu:system');
insert into menu values(2, '用户管理', '', 0, 'menu:user');
insert into role values(1, '管理员');
insert into role values(2, '普通用户');
insert into user_role values(1, 1);
insert into user_role values(2, 2);
insert into role_menu values(1, 1);
insert into role_menu values(1, 2);
insert into role_menu values(2, 1);