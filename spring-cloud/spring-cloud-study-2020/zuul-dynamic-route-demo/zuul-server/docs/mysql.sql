
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zuul-test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zuul-test`;

DROP TABLE IF EXISTS `zuul_route`;

CREATE TABLE `zuul_route` (
  `id` varchar(50) NOT NULL,
  `path` varchar(255) NOT NULL,
  `service_id` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `strip_prefix` tinyint(1) DEFAULT '1',
  `retryable` tinyint(1) DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO zuul_route (id,`path`,service_id,url,strip_prefix,retryable,enabled,description) VALUES ('1','/baidu/**',NULL,'http://www.baidu.com',1,0,1,'重定向百度');
INSERT INTO zuul_route (id,`path`,service_id,url,strip_prefix,retryable,enabled,description) VALUES ('2','/provider/service/**',NULL,'http://localhost:9090',1,0,1,'url');
INSERT INTO zuul_route (id,`path`,service_id,url,strip_prefix,retryable,enabled,description) VALUES ('3','/provider/service/**','provider-service',NULL,1,0,1,'serviceId');


