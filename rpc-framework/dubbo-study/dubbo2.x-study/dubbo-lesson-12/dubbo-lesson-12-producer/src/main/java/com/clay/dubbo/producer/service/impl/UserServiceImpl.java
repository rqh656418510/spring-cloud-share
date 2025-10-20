package com.clay.dubbo.producer.service.impl;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 暴露 Dubbo 服务
 */
@DubboService(executes = 2)
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Long id) {
        try {
            // 模拟业务耗时处理
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User(id, "Peter", 18);
    }

}
