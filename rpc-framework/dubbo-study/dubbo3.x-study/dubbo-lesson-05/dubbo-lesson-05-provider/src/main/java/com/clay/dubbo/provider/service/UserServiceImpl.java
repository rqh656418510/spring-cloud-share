package com.clay.dubbo.provider.service;

import com.clay.dubbo.api.HelloRequest;
import com.clay.dubbo.api.HelloResponse;
import com.clay.dubbo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

/**
 * 服务提供者（暴露服务）
 */
@Slf4j
@DubboService(serialization = "protobuf")
public class UserServiceImpl implements UserService {

    @Override
    public HelloResponse sayHello(HelloRequest request) {
        log.info("request from consumer: {}", RpcContext.getContext().getRemoteAddress());
        return HelloResponse.newBuilder().setMessage("Hello " + request.getName()).build();
    }

    @Override
    public CompletableFuture<HelloResponse> sayHelloAsync(HelloRequest request) {
        return CompletableFuture.completedFuture(sayHello((request)));
    }

}
