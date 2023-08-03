package com.clay.boot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author clay
 */
@Slf4j
@Component
public class CustomApplicationListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log.info("===> 正在启动");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
                                    ConfigurableEnvironment environment) {
        log.info("===> 环境准备完成");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("===> IOC 容器准备完成");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("===> IOC 容器加载完成");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("===> 启动完成");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("===> 准备就绪");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("==> 应用启动失败");
    }

}
