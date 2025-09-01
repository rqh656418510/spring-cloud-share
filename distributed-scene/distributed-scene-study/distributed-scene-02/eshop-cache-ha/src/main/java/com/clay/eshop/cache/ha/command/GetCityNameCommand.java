package com.clay.eshop.cache.ha.command;

import com.clay.eshop.cache.ha.local.LocationCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 获取城市名称（基于本地缓存）
 */
public class GetCityNameCommand extends HystrixCommand<String> {

    private final Long cityId;

    public GetCityNameCommand(Long cityId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("GetCityNameCommand"))
            // 信号量隔离的配置
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(20)));
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        return LocationCache.getCityName(this.cityId);
    }
}
