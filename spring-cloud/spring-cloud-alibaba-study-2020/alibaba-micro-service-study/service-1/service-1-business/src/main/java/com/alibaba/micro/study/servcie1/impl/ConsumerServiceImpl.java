package com.alibaba.micro.study.servcie1.impl;

import com.alibaba.micro.study.servcie1.api.ConsumerService;
import com.alibaba.micro.study.service2.api.ProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用 @DubboService 注解标记此类的方法暴露为Dubbo接口
 */
@DubboService
public class ConsumerServiceImpl implements ConsumerService {

    /**
     * 生成接口代理对象，通过代理对象进行远程调用
     */
    @DubboReference
    private ProviderService providerService;

    private Logger LOG = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @Override
    public String add(Integer a, Integer b) {
        LOG.info("service 1 business invoke");
        return providerService.add(a, b);
    }
}
