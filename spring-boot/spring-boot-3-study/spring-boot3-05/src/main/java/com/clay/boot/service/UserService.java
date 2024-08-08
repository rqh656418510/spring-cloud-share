package com.clay.boot.service;

import com.clay.boot.domain.User;
import com.clay.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author clay
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(Long id) {
        return userMapper.getById(id);
    }

}
