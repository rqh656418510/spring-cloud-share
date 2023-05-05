create table if not exists `t_user` (
	`id`       int primary key auto_increment not null,
	`username` char (50) not null,
	`pwd`      char(50) not null,
	`create_time` datetime not null,
	`update_time` datetime
);
