create database distributed_database_study_01 default character set utf8;
create database distributed_database_study_02 default character set utf8;
create database distributed_database_study_03 default character set utf8;
create database distributed_database_study_04 default character set utf8;
create database distributed_database_study_05 default character set utf8;
create database distributed_database_study_06 default character set utf8;

create table `t_employee` (
  `id` int(11) not null auto_increment,
  `last_name` varchar(255) default null,
  `gender` char(1) default null,
  `email` varchar(255) default null,
  `dept_id` int(11) default null,
  primary key (`id`)
) engine=innodb default charset=utf8;

insert into t_employee(id, last_name, gender, email) values(1, 'Jim','1', 'jim@gmail.com');
insert into t_employee(id, last_name, gender, email) values(2, 'Tom','1', 'tom@gmail.com');
insert into t_employee(id, last_name, gender, email) values(3, 'Peter','1', 'peter@gmail.com');
