package com.clay.h2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.h2.entity.User;
import com.clay.h2.mapper.UserMapper;
import com.clay.h2.service.UserService;
import com.clay.h2.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author clay
 * @date 2023-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public Result getByPage() {
		Page<User> page = new Page<>(1, 10);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", "zhhangsan");
		this.page(page, wrapper);
		return new Result(page.getRecords());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result add(User user) {
		user.setCreateTime(new Date());
		return new Result(this.save(user));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result clear() {
		this.baseMapper.clear();
		return new Result();
	}

}
