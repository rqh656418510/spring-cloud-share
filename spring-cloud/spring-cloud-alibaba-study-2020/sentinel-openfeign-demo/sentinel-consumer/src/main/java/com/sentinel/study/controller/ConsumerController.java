package com.sentinel.study.controller;

import com.sentinel.study.agent.FeignAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class ConsumerController {

    @Autowired
    private FeignAgent feignAgent;

    private Logger LOG = LoggerFactory.getLogger(ConsumerController.class);

    @GetMapping("/hello")
    public String hello() {
        LOG.info("consumer invoke ... ");
        return feignAgent.hello();
    }
}
