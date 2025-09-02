package com.clay.eshop.cache.ha.command;

import com.clay.eshop.cache.ha.local.BrandCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * 获取品牌名称
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private final Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandServiceGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandNameCommand"))
            // 线程池的配置（默认使用线程池隔离策略）
            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                .withCoreSize(20)
                .withQueueSizeRejectionThreshold(20))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                // 降级机制的配置
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(10)
                // 超时机制配置
            .withExecutionTimeoutInMilliseconds(2000)));
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        // 直接抛出异常（模拟远程调用失败），触发降级机制
        throw new RuntimeException();
    }

    /**
     * 降级逻辑
     */
    @Override
    protected String getFallback() {
        System.out.println("==> 触发降级机制，从本地缓存获取品牌数据, brandId = " + this.brandId);
        // 从本地缓存获取数据
        return BrandCache.getBrandName(this.brandId);
    }

}