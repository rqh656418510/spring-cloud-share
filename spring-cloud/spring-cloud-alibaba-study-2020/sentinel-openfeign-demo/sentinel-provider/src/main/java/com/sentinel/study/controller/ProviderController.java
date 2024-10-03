package com.sentinel.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class ProviderController {

    private Logger LOG = LoggerFactory.getLogger(ProviderController.class);

    @GetMapping("/hello")
    public String hello() {
        LOG.info("provider invoke ... ");
        return "Hello Sentinel!";
    }
}
