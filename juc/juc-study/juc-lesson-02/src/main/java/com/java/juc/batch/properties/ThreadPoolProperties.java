package com.java.juc.batch.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolProperties {

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize;

    /**
     * 工作队列的最大长度
     */
    private int queueCapacity;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds;

}