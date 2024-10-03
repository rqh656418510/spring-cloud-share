package com.srpingcloud.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @GetMapping("/v1")
    public String v1() {
        return "version: v1";
    }

    @GetMapping("/v2")
    public String v2() {
        return "version: v2";
    }
}
