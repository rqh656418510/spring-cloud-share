package com.clay.boot.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MyBizService {

    private final Counter counter;

    /**
     * 自动注入 MeterRegistry，用于保存和统计所有自定义指标
     */
    public MyBizService(MeterRegistry meterRegistry) {
        // 获取一个名称是 myBizCounter 的计数器
        counter = meterRegistry.counter("myBizCounter");
    }

    /**
     * 处理业务逻辑
     */
    public void process() {
        // 统计业务方法被调用的次数
        counter.increment();
    }

}
