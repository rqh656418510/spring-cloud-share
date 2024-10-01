package com.turing.cloud.api;

import com.turing.cloud.entities.Pay;
import com.turing.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author turing
 * @version 1.0
 */
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    /**
     * 新增支付流水
     */
    @PostMapping("/pay/add")
    ResultData<String> addPay(@RequestBody Pay pay);

    /**
     * 按照 ID 查支付流水
     */
    @GetMapping("/pay/get/{id}")
    ResultData<Pay> getById(@PathVariable("id") Integer id);

    /**
     * 获取微服务应用的信息
     */
    @GetMapping("/pay/get/appinfo")
    ResultData<String> getAppInfo();

    /**
     * 该接口用于测试服务调用方（消费者）的断路器
     */
    @GetMapping(value = "/pay/circuit/{id}")
    ResultData<String> circuit(@PathVariable("id") Integer id);

    /**
     * 该接口用于测试 Resilience4j 的隔离（舱壁）
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    ResultData<String> bulkhead(@PathVariable("id") Integer id);

    /**
     * 该接口用于测试 Resilience4j 的限流
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    ResultData<String> rateLimit(@PathVariable("id") Integer id);

    /**
     * 该接口用于测试 Micrometer 链路监控
     */
    @GetMapping("/pay/micrometer/{id}")
    ResultData<String> micrometer(@PathVariable("id") Integer id);

}
