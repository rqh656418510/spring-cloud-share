create database distributed_database_study_01 default character set utf8;
create database distributed_database_study_02 default character set utf8;
create database distributed_database_study_03 default character set utf8;
create database distributed_database_study_04 default character set utf8;
create database distributed_database_study_05 default character set utf8;

create table `user` (
    `id` bigint(20) not null auto_increment,
    `username` varchar(25) default null,
    `age` tinyint default null,
    `phone` bigint(20) default null,
    `email` varchar(255) default null,
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
