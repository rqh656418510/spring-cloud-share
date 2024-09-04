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

}
