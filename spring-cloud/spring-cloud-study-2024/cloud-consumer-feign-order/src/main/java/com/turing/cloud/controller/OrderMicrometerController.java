package com.turing.cloud.controller;

import com.turing.cloud.api.PayFeignApi;
import com.turing.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderMicrometerController {

    @Autowired
    private PayFeignApi payFeignApi;

    /**
     * 该接口用于测试 Micrometer 链路监控
     */
    @GetMapping("/feign/pay/micrometer/{id}")
    public ResultData<String> micrometer(@PathVariable("id") Integer id) {
        return payFeignApi.micrometer(id);
    }

}
