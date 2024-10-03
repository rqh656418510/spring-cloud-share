package com.nacos.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value(("${common.address}"))
    private String address;

    @Value("${common.threads}")
    private String threads;

    @GetMapping("/get")
    public String get() {
        return "address: " + address + " threads: " + threads;
    }
}
