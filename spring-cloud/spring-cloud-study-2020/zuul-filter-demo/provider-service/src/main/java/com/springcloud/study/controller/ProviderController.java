package com.springcloud.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProviderController {

    @GetMapping("/provider/add")
    public String add(Integer a, Integer b, HttpServletRequest request) {
        return "From Port: " + request.getServerPort() + ", Result: " + (a + b);
    }
}
