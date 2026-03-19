package com.clay.wms.config;

import com.clay.wms.interceptor.GrayFeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Feign 全局配置
 */
public class GlobalFeignConfiguration {

    @Bean
    public RequestInterceptor grayFeignInterceptor() {
        return new GrayFeignInterceptor();
    }

}