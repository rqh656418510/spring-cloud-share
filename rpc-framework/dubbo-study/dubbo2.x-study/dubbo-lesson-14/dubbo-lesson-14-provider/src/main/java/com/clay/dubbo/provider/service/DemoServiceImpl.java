package com.clay.dubbo.provider.service;

import com.clay.dubbo.thrift.DemoThrift;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.thrift.TException;

/**
 * 暴露服务
 */
@DubboService
public class DemoServiceImpl implements DemoThrift.Iface {

    @Override
    public String sayHello(String name) throws TException {
        return "Hello " + name;
    }

}
