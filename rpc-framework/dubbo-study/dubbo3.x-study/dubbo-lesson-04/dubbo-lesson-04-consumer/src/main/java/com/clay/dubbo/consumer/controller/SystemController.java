package com.clay.dubbo.consumer.controller;

import com.clay.dubbo.domain.User;
import com.clay.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {

    /**
     * 引用 Dubbo 服务
     * <p> 可以指定版本、负载均衡算法、重试次数、超时毫秒等。
     * <p> 负载均衡默认为 random，还可以配置为：roundrobin 、leastactive 、consistenthash
     * <p> 超时毫秒数 timeout 可以统一在 application.yml 进行配置，也可以在具体服务上做个性化配置
     * <p> 重试次数 retries 可以统一在 application.yml 进行配置，也可以在具体服务上做个性化配置，但是对于新增（插入）等非幂等操作，建议不要重试
     */
    @DubboReference
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user) {
        return userService.update(user);
    }

}
