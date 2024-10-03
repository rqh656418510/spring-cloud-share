package com.clay.config;

import com.clay.retry.CustomRetryer;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * 当前配置类没有被 @Configuration 注解标注，不然该配置类默认会在全局生效
 *
 * @author clay
 * @version 1.0
 */
public class FeignRetryerConfig {

    @Bean
    public Retryer feignRetryer() {
        return new CustomRetryer(100, 5);
    }

}
