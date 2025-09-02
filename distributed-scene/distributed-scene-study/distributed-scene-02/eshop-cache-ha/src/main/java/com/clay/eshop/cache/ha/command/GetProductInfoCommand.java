package com.clay.eshop.cache.ha.command;

import com.alibaba.fastjson.JSONObject;
import com.clay.eshop.cache.ha.http.HttpClientUtils;
import com.clay.eshop.cache.ha.model.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * 获取单个商品的信息
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private final Long productId;

    private static final String KEY_PREFIX = "product_info_";

    private static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");

    public GetProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductServiceGroup"))
            .andCommandKey(COMMAND_KEY)
            // 线程池的配置（默认使用线程池隔离策略）
            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                .withCoreSize(20)
                .withQueueSizeRejectionThreshold(20))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                // 熔断机制的配置
                .withCircuitBreakerRequestVolumeThreshold(15)
                .withCircuitBreakerErrorThresholdPercentage(40)
                .withCircuitBreakerSleepWindowInMilliseconds(6000)
                // 降级机制的配置
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(10)
                // 超时机制配置
                .withExecutionTimeoutInMilliseconds(2000)));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        // 用于触发熔断机制
        if (this.productId == -1L) {
            throw new RuntimeException();
        }

        // 调用商品服务的接口，获取商品ID对应的商品的最新数据，用HttpClient去调用商品服务的Http接口
        String url = "http://127.0.0.1:9092/product/info?productId=" + productId;
        System.out.println("Send request by " + url);
        String response = HttpClientUtils.sendGetRequest(url);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    /**
     * 降级逻辑
     */
    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(0L);
        productInfo.setName("降级商品");
        return productInfo;
    }

    /**
     * 指定请求缓存的Key
     */
    @Override
    protected String getCacheKey() {
        return KEY_PREFIX + this.productId;
    }

    /**
     * 清理指定的请求缓存
     */
    public static void cleanCache(Long productId) {
        HystrixRequestCache.getInstance(COMMAND_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(KEY_PREFIX + productId);
    }

}