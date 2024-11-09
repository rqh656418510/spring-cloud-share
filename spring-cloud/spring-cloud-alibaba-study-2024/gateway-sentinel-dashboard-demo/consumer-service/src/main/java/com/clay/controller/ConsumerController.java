package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
import com.clay.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 * @version 1.0
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private ProviderFeignApi providerFeignApi;

    @GetMapping("/pay/get/{orderNumber}")
    public ResultData getPayByOrderNumber(@PathVariable("orderNumber") String orderNumber) {
        return providerFeignApi.getPayByOrderNumber(orderNumber);
    }

}