package com.springcloud.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CalculateController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 这里的"PROVIDER"是Provider服务的名称（大写英文字母）
     */
    @GetMapping("/add")
    public String add(Integer a, Integer b) {
        String result = restTemplate.getForObject("http://PROVIDER/provider/add?a=" + a + "&b=" + b, String.class);
        return result;
    }
}