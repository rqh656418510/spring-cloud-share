package com.clay.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/add")
    public String add(Integer a, Integer b, HttpServletRequest request) {
        String result = restTemplate.getForObject("http://provider-service/provider/add?a=" + a + "&b=" + b, String.class);
        return result;
    }

}