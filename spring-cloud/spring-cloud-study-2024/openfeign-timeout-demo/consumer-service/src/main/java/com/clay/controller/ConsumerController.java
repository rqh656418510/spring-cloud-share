package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private ProviderFeignApi providerFeignApi;

    @GetMapping("/consumer/add")
    public String add(Integer a, Integer b) {
        return providerFeignApi.add(a, b);
    }

    @GetMapping("/consumer/divide")
    public String divide(Integer a, Integer b) {
        String result = null;
        try {
            result = providerFeignApi.divide(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}