package com.turing.cloud.controller;

import com.turing.cloud.dto.PayDTO;
import com.turing.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author turing
 * @version 1.0
 */
@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/" + id, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/appinfo")
    public ResultData getAppInfo() {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/appinfo", ResultData.class);
    }

}
