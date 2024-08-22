package com.turing.cloud.controller;

import com.turing.cloud.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AppProperties properties;

    @GetMapping("/info")
    public String info() {
        return properties.getInfo();
    }

}
