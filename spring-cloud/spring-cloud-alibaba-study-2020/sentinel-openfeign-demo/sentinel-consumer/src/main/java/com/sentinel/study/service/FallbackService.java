package com.sentinel.study.service;

import com.sentinel.study.agent.FeignAgent;
import org.springframework.stereotype.Component;

/**
 * 限流、降级的回调类
 *
 * @author clay
 */
@Component
public class FallbackService implements FeignAgent {

    @Override
    public String hello() {
        return "系统繁忙，请稍候 ...";
    }
}
