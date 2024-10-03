package com.clay.controller;

import com.clay.apis.ProviderFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
        System.out.println("--- 开始调用 --- " + sdf.format(new Date()));

        try {
            result = providerFeignApi.divide(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--- 结束调用 --- " + sdf.format(new Date()));
        return result;
    }

}