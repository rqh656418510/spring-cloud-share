package com.springcloud.study.config;

import com.springcloud.study.filter.PostFilter;
import com.springcloud.study.filter.SecondPreFilter;
import com.springcloud.study.filter.ThirdPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public SecondPreFilter firstPreFilter() {
        return new SecondPreFilter();
    }

    @Bean
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
}
