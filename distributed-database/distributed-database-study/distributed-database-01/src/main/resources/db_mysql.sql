create database distributed_database_study_01 default character set utf8;
create database distributed_database_study_02 default character set utf8;
create database distributed_database_study_03 default character set utf8;
create database distributed_database_study_04 default character set utf8;
create database distributed_database_study_05 default character set utf8;

CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_employee(id, last_name, gender, email) values(1, 'Jim','1', 'jim@gmail.com');
insert into t_employee(id, last_name, gender, email) values(2, 'Tom','1', 'tom@gmail.com');
insert into t_employee(id, last_name, gender, email) values(3, 'Peter','1', 'peter@gmail.com');
