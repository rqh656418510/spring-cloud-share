package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 */
@RestController
public class OriginControlController {

    private static final String RESOURCE_NAME = "Origin";

    /**
     * @return
     * @SentinelResource 定义资源
     * value：资源名称
     * blockHandler：被限制访问时处理的方法
     */
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "exceptionHandler")
    @GetMapping("/hello")
    public String hello() {
        return "Hello Sentinel!";
    }

    /**
     * 原方法被限制访问的时候调用此方法
     *
     * @param e
     * @return
     */
    public String exceptionHandler(BlockException e) {
        return "系统繁忙，请稍候 ...";
    }

    /**
     * 定义来源访问控制规则（黑名单）
     */
    @PostConstruct
    public void initBlackRule() {
        // 创建存放规则的集合
        List<AuthorityRule> rules = new ArrayList<>();
        // 创建来源访问控制规则
        AuthorityRule rule = new AuthorityRule();
        // 定义资源名称
        rule.setResource(RESOURCE_NAME);
        // 定义限制模式
        rule.setStrategy(RuleConstant.AUTHORITY_BLACK);
        // 定义请求来源
        rule.setLimitApp("127.0.0.1");
        // 将规则保存到集合中
        rules.add(rule);
        // 加载规则
        AuthorityRuleManager.loadRules(rules);
    }
}
