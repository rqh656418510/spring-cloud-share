CREATE TABLE `t_barrage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `content` varchar(50) DEFAULT NULL COMMENT '弹幕内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

insert t_barrage(`user_id`, `content`) values('16589238', '哈哈');
insert t_barrage(`user_id`, `content`) values('19589332', '破防了');
insert t_barrage(`user_id`, `content`) values('12559238', '买买买');
