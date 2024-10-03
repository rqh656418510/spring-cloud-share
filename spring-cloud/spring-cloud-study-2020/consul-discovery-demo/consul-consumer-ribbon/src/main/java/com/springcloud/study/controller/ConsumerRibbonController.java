package com.springcloud.study.controller;

import com.springcloud.study.client.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerRibbonController {

    private static final String URL = "http://consul-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderService providerService;

    @GetMapping("/actuator/health")
    public String health() {
        return "SUCCESS";
    }

    @GetMapping("/consumer/sayHelloOne/{name}")
    public String sayHelloOne(@PathVariable("name") String name) {
        return providerService.sayHello(name);
    }

    @GetMapping("/consumer/sayHelloTwo/{name}")
    public String sayHelloTwo(@PathVariable("name") String name) {
        return restTemplate.getForObject(URL + "/provider/sayHello/" + name, String.class);
    }

}
