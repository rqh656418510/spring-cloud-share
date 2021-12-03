package com.clay.auth.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.common.entity.User;
import com.clay.auth.mapper.UserMapper;
import com.clay.common.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author clay
 * @version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }

}
