package com.clay.eshop.cache.ha.command;

import com.clay.eshop.cache.ha.local.LocationCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 获取城市名称（基于本地缓存）
 */
public class GetCityNameCommand extends HystrixCommand<String> {

    private final Long cityId;

    public GetCityNameCommand(Long cityId) {
        // 配置隔离策略为信号量隔离
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        return LocationCache.getCityName(this.cityId);
    }
}
