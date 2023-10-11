package com.clay.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.acl.entity.User;
import com.clay.acl.mapper.UserMapper;
import com.clay.acl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author clay
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public User selectByUsername(String username) {
		return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
	}

}
