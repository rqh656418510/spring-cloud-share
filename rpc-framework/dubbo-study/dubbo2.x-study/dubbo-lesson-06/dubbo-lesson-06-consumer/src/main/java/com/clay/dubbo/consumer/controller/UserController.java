package com.clay.dubbo.consumer.controller;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 引用 Dubbo 服务
     */
    @DubboReference(version = "2.0", loadbalance = "random", cluster = "failover", mock = "fail:return null")
    private UserService userService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return userService.sayHello(name);
    }

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

}
