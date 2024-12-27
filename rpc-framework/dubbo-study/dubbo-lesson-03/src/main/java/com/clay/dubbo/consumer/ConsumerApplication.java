package com.clay.dubbo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.clay.dubbo.service.DemoService;

import java.io.IOException;

public class ConsumerApplication {

    public static void main(String[] args) throws IOException {
        // 服务信息
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-consumer-application");
        applicationConfig.setQosEnable(true);
        applicationConfig.setQosPort(22223);
        applicationConfig.setQosAcceptForeignIp(false);

        // 注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.56.112");
        registryConfig.setPort(2181);
        registryConfig.setTimeout(5000);

        // 引用远程服务
        // ReferenceConfig 内部封装了与注册中心的连接，以及与服务提供者的连接
        // ReferenceConfig 实例很重，请自行缓存，否则可能会造成内存和连接泄漏
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");

        // 调用远程服务
        // DemoService 代理对象比较重，内部封装了所有通信细节，请缓存使用
        DemoService demoService = referenceConfig.get();
        String result = demoService.sayHello("Dubbo");
        System.out.println("===> " + result);
        System.in.read();
    }

}
