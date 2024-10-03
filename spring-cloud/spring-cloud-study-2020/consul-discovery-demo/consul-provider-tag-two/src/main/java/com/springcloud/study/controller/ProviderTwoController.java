package com.springcloud.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderTwoController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/actuator/health")
    public String health() {
        return "SUCCESS";
    }

    @GetMapping("/provider/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "from port " + port + ": hello " + name;
    }
}
