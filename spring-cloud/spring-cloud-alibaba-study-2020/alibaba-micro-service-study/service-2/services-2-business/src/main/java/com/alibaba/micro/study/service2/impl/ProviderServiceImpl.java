package com.alibaba.micro.study.service2.impl;

import com.alibaba.micro.study.service2.api.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用 @DubboService 注解标记此类的方法暴露为Dubbo接口
 */
@DubboService
public class ProviderServiceImpl implements ProviderService {

    private Logger LOG = LoggerFactory.getLogger(ProviderServiceImpl.class);

    @Override
    public String add(Integer a, Integer b) {
        LOG.info("service 2 business invoke");
        return String.valueOf(a + b);
    }

    @Override
    public String sub(Integer a, Integer b) {
        LOG.info("service 2 business invoke");
        return String.valueOf(a - b);
    }
}
