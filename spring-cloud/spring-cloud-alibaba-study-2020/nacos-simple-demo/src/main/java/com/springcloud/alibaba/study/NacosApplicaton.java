package com.springcloud.alibaba.study;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author clay
 */
public class NacosApplicaton {

    public static void main(String[] args) throws NacosException, IOException {
        String serverAddr = "127.0.0.1:8848";
        String group = "DEFAULT_GROUP";
        String dataId = "nacos_simple_demo.yaml";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 1000);
        System.out.println(content);

        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println(configInfo);
            }
        });
        System.in.read();
    }
}
