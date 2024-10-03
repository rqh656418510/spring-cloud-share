package com.clay.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * 当前配置类没有被 @Configuration 注解标注，不然该配置类默认会在全局生效
 *
 * @author turing
 * @version 1.0
 */
public class FeignRetryerConfig {

    @Bean
    public Retryer feignRetryer() {
        // 初始间隔时间为100ms，重试最大间隔时间为1000ms，最大请求次数为3（1次初始调用，4次重试调用）
        return new Retryer.Default(100, 1000, 5);
    }

}