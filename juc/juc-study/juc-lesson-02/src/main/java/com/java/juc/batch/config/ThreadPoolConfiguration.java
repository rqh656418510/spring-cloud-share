package com.java.juc.batch.config;

import com.java.juc.batch.properties.ThreadPoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfiguration {

    @Resource
    private ThreadPoolProperties threadPoolProperties;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        // 这里使用 Spring 的 ThreadPoolTaskExecutor，而不是 JUC 的 ThreadPoolExecutor
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        // 核心线程数
        threadPool.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 最大可创建的线程数
        threadPool.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 工作队列的最大长度
        threadPool.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 线程池维护线程所允许的空闲时间
        threadPool.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        // 异步方法内部线程的名称前缀
        threadPool.setThreadNamePrefix("Spring默认线程池 - ");
        // 线程池对拒绝任务的处理策略
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 任务都执行完成再关闭线程池
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 任务初始化
        threadPool.initialize();

        return threadPool;
    }

}
