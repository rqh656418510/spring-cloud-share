package com.clay.dubbo.provider;

import com.clay.dubbo.provider.service.DemoServiceImpl;
import com.clay.dubbo.service.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class ProviderApplication {

    public static void main(String[] args) throws IOException {
        // 服务实现
        DemoService demoService = new DemoServiceImpl();

        // 服务信息
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-provider-application");
        applicationConfig.setQosEnable(true);
        applicationConfig.setQosPort(22222);
        applicationConfig.setQosAcceptForeignIp(false);

        // 注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.2.235");
        registryConfig.setPort(2181);
        registryConfig.setTimeout(5000);

        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        protocolConfig.setThreads(200);

        // 服务配置
        // ServiceConfig 内部封装了与注册中心的连接，以及开启服务端口
        // ServiceConfig 实例很重，请自行缓存，否则可能会造成内存和连接泄漏
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();

        System.in.read();
    }

}
