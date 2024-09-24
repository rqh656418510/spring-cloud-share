DROP USER cloud;

CREATE USER 'cloud'@'%' identified BY '123456';

GRANT ALL PRIVILEGES ON spring_cloud_2024.* TO 'cloud'@'%';

FLUSH PRIVILEGES;
