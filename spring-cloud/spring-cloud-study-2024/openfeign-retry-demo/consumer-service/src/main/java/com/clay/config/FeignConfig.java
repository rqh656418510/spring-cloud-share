package com.clay.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局设置 Feign 的失败重试次数
 *
 * @author clay
 * @version 1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer() {
        // 初始间隔时间为100ms，重试最大间隔时间为1000ms，最大请求次数为3（1次初始调用，2次重试调用）
        return new Retryer.Default(100, 1000, 3);
    }

}