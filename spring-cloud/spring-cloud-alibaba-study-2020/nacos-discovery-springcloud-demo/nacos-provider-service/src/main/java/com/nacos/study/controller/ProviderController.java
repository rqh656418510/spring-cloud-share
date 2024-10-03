package com.nacos.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("/call")
    public String call() {
        return "provider invoke";
    }
}
