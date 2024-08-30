CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `age` tinyint DEFAULT NULL COMMENT '年龄',
  `status` tinyint DEFAULT NULL COMMENT '有效状态，1有效，0无效',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

insert t_user(`phone`, `name`, `age`, `status`) values('911', 'Jim', 18, 1);
insert t_user(`phone`, `name`, `age`, `status`) values('911', 'Amy', 20, 1);
insert t_user(`phone`, `name`, `age`, `status`) values('911', 'Tom', 25, 1);
insert t_user(`phone`, `name`, `age`, `status`) values('911', 'Peter', 28, 1);
