package com.clay.dubbo.producer.service.impl;

import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 暴露 Dubbo 服务
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
