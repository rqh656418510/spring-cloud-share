package com.clay.dubbo.consumer.controller;

import com.clay.dubbo.HelloRequest;
import com.clay.dubbo.HelloResponse;
import com.clay.dubbo.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务消费者
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    /**
     * 引用 Dubbo 服务
     */
    @DubboReference
    private UserService userService;

    @GetMapping("/sayHello/{name}")
    public String getUser(@PathVariable("name") String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response = userService.sayHello(request);
        log.info("result: {}", response.getMessage());
        return response.getMessage();
    }

}