package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author clay
 */
@RestController
public class DegradeController {

    private static final String RESOURCE_NAME = "Degrade";

    private Logger LOG = LoggerFactory.getLogger(DegradeController.class);

    /**
     * @return
     * @SentinelResource 定义资源
     * value：资源名称
     * blockHandler：熔断降级处理的方法
     */
    @SentinelResource(value = RESOURCE_NAME, fallback = "exceptionHandler")
    @GetMapping("/hello")
    public String hello() {
        // 被保护的资源
        try {
            Random random = new Random();
            int millis = random.nextInt(10);
            LOG.info("sleep time: " + millis);
            // 随机休眠10毫秒以内
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    /**
     * 原方法被熔断降级的时候调用此方法
     *
     * @return
     */
    public String exceptionHandler() {
        LOG.error("fallback handler invoke");
        return "系统繁忙，请稍候 ...";
    }

    /**
     * 定义熔断降级规则
     */
    @PostConstruct
    public void initDegradeRule() {
        // 创建存放熔断降级规则的集合
        List<DegradeRule> rules = new ArrayList<>();
        // 创建熔断降级规则
        DegradeRule rule = new DegradeRule();
        // 定义资源名称
        rule.setResource(RESOURCE_NAME);
        // 定义熔断降级规则的类型
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 定义降级熔断时间（单位 s）
        rule.setTimeWindow(5);
        // 定义慢调用临界RT（超出该值计为慢调用，单位 s）
        rule.setCount(0.005);
        // 定义熔断触发的最小请求数
        rule.setMinRequestAmount(1);
        // 定义统计时长（单位为 ms）
        rule.setStatIntervalMs(1000);
        // 定义慢调用比例阈值
        rule.setSlowRatioThreshold(0.5);
        // 将熔断降级规则添加到集合中
        rules.add(rule);
        // 加载熔断降级规则
        DegradeRuleManager.loadRules(rules);
    }
}
