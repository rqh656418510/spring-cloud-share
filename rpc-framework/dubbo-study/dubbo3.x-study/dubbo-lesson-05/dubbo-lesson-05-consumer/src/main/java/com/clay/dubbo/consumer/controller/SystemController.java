package com.clay.dubbo.consumer.controller;

import com.clay.dubbo.api.HelloRequest;
import com.clay.dubbo.api.HelloResponse;
import com.clay.dubbo.api.UserService;
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
     * <p> 可以指定版本、负载均衡算法、重试次数、超时毫秒等。
     * <p> 负载均衡默认为 random，还可以配置为：roundrobin 、leastactive 、consistenthash
     * <p> 超时毫秒数 timeout 可以统一在 application.yml 进行配置，也可以在具体服务上做个性化配置
     * <p> 重试次数 retries 可以统一在 application.yml 进行配置，也可以在具体服务上做个性化配置，但是对于新增（插入）等非幂等操作，建议不要重试
     */
    @DubboReference
    private UserService userService;

    @GetMapping("/sayHello/{name}")
    public String getUser(@PathVariable("name") String name) {
        // RPC 请求的参数
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        // 发起 RPC 请求
        HelloResponse response = userService.sayHello(request);
        // 获取 RPC 请求的结果
        log.info("result: {}", response.getMessage());
        return response.getMessage();
    }

}