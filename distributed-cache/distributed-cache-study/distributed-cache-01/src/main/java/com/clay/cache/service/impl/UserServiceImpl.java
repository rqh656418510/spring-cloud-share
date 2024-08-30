package com.clay.cache.service.impl;

import com.clay.cache.annotations.RedisCacheable;
import com.clay.cache.entity.User;
import com.clay.cache.mapper.UserMapper;
import com.clay.cache.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    @RedisCacheable(keyPrefix = "User", matchValue = "#id")
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
