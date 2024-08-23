package com.turing.cloud.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author turing
 * @version 1.0
 */
@Getter
@RefreshScope
@Configuration
public class AppProperties {

    @Value("${env.info}")
    private String info;

}
