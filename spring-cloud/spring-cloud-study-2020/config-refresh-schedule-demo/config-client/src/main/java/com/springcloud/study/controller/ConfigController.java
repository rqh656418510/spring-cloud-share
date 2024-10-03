package com.springcloud.study.controller;

import com.springcloud.study.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Autowired
    public ConfigProperties configProperties;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        return configProperties.getConfig();
    }

}
