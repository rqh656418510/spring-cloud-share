package com.turing.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author turing
 * @version 1.0
 */
@Service
public class FlowLimitService {

    /**
     * 使用注解定义 Sentinel 资源
     */
    @SentinelResource(value = "common")
    public void common() {
        System.out.println("------FlowLimitService come in");
    }

}
