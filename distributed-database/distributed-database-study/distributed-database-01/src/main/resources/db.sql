create table `user` (
    `id` bigint(20) not null auto_increment,
    `username` varchar(25) default null,
    `age` tinyint default null,
    `phone` bigint(20) default null,
    `email` varchar(255) default null,
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
