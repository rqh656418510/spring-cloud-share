package com.clay.eshop.cache.ha.config;

import com.clay.eshop.cache.ha.filter.HystrixRequestContextFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        // 注册Hystrix请求上下文的过滤器
        FilterRegistrationBean registration = new FilterRegistrationBean(new HystrixRequestContextFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}