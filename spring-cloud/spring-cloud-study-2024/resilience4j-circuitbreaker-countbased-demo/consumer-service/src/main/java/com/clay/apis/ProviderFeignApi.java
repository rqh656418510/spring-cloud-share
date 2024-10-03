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
     * 该接口用于测试服务调用方（消费者）的断路器
     */
    @GetMapping(value = "/provider/circuit/{id}")
    String circuit(@PathVariable("id") Integer id);

}
