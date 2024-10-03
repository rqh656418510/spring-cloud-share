package com.alibaba.micro.study.application1.controller;

import com.alibaba.micro.study.servcie1.api.ConsumerService;
import com.alibaba.micro.study.service2.api.ProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    /**
     * 生成接口代理对象，通过代理对象进行远程调用
     */
    @DubboReference
    private ConsumerService consumerService;

    /**
     * 生成接口代理对象，通过代理对象进行远程调用
     */
    @DubboReference
    private ProviderService providerService;

    private Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping("/add")
    public String add(Integer a, Integer b) {
        LOG.info("application invoke");
        return consumerService.add(a, b);
    }

    @GetMapping("/sub")
    public String sub(Integer a, Integer b) {
        LOG.info("application invoke");
        return providerService.sub(a, b);
    }
}
