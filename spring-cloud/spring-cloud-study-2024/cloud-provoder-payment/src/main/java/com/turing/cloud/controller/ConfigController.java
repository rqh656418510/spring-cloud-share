package com.turing.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${env.info}")
    private String info;

    @GetMapping("/info")
    public String info() {
        return info;
    }

}
