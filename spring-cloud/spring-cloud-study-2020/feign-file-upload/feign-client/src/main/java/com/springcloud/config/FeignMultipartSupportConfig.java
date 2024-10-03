package com.springcloud.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign Client 配置（非全局生效）
 */
@Configuration
public class FeignMultipartSupportConfig {

    @Bean
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }
}
