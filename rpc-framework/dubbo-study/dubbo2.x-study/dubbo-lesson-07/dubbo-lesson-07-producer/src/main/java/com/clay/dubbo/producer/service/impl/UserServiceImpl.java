package com.clay.dubbo.producer.service.impl;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 暴露 Dubbo 服务
 */
@DubboService(protocol = {"dubbo", "rest"})
public class UserServiceImpl implements UserService {

    @Override
    public Boolean add(User user) {
        System.out.println(user);
        return true;
    }

    @Override
    public User getById(Long id) {
        return new User(id, "Peter", 18);
    }

}
