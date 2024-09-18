drop user cloud;

create user 'cloud'@'%' identified by '123456';

grant all privileges on spring_cloud_2024.* to 'cloud'@'%';

flush privileges;
