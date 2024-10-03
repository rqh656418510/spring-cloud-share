package com.clay.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author clay
 * @version 1.0
 */
@FeignClient("provider-service")
public interface ProviderFeignApi {

    /**
     * 该接口用于测试 Resilience4j 的限流
     */
    @GetMapping(value = "/provider/ratelimit/{id}")
    String rateLimit(@PathVariable("id") Integer id);

}
