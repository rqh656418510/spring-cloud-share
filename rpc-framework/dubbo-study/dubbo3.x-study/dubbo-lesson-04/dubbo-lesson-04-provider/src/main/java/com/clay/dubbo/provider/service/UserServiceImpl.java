package com.clay.dubbo.provider.service;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 暴露服务
 */

@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public Boolean update(User user) {
        System.out.println(user);
        return true;
    }

    @Override
    public User getById(Long id) {
        return new User(id, "Peter", 18);
    }

}
