package com.clay.dubbo.producer.service.impl;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 暴露 Dubbo 服务
 */
@DubboService(timeout = 1000, retries = 3, version = "2.0")
public class UserServiceImpl2 implements UserService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public User getById(Long id) {
        System.out.println("===> invoke getById() v2.0");
        try {
            // 模拟业务耗时处理
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User(id, "Peter", 18);
    }

}
