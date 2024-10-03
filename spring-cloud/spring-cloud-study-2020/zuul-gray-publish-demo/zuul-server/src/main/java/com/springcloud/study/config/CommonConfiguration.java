package com.springcloud.study.config;

import com.springcloud.study.filter.GrayPublishFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public GrayPublishFilter grayPublishFilter() {
        return new GrayPublishFilter();
    }

}
