package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 */
@RestController
public class SystemProtectController {

    /**
     * 定义资源
     * EntryType.IN 表示入口资源
     *
     * @return
     */
    @SentinelResource(entryType = EntryType.IN)
    @GetMapping("/hello")
    public String hello() {
        return "Hello Sentinel!";
    }

    /**
     * 定义系统自适应保护规则
     */
    @PostConstruct
    public void initSystemRule() {
        // 创建存放系统自适应保护规则的集合
        List<SystemRule> rules = new ArrayList<>();
        // 创建系统自适应保护规则
        SystemRule rule = new SystemRule();
        // 定义入口资源的QPS（每秒允许的最大请求数）
        rule.setQps(2);
        // 添加系统自适应保护规则到集合中
        rules.add(rule);
        // 加载系统自适应保护规则
        SystemRuleManager.loadRules(rules);
    }
}
