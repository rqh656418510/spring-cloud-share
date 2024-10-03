package com.clay.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author clay
 * @version 1.0
 */
@RestController
public class ProviderController {

    @GetMapping("/provider/add")
    public String add(Integer a, Integer b, HttpServletRequest request) {
        return "From Port: " + request.getServerPort() + ", Result: " + (a + b);
    }

    @GetMapping("/provider/divide")
    public String divide(Integer a, Integer b, HttpServletRequest request) {
        System.out.println("=========== invoke divide() ===========");
        try {
            // 模拟业务处理超时
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "From Port: " + request.getServerPort() + ", Result: " + (a / b);
    }

}