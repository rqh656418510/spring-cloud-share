package com.clay.dubbo.provider.service;

import com.clay.dubbo.api.DubboUserServiceTriple;
import com.clay.dubbo.api.HelloRequest;
import com.clay.dubbo.api.HelloResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 服务提供者（暴露服务）
 */
@Slf4j
@DubboService
public class UserServiceImpl extends DubboUserServiceTriple.UserServiceImplBase {

    @Override
    public HelloResponse sayHello(HelloRequest request) {
        log.info("Hello, " + request.getName());
        return HelloResponse.newBuilder().setMessage("Hello, " + request.getName() + "!").build();
    }

}
