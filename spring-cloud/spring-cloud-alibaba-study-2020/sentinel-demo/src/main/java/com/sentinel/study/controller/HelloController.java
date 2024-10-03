package com.sentinel.study.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 */
@RestController
public class HelloController {

    /**
     * 资源名称
     */
    private static final String RESOURCE_NAME = "Hello";

    @GetMapping("/hello")
    public String hello() {
        // 使用流控规则
        try (Entry entry = SphU.entry(RESOURCE_NAME)) {
            // 被保护的资源
            return "Hello Sentinel!";
        } catch (Exception e) {
            // 被限流
            e.printStackTrace();
            return "系统繁忙，请稍后 ...";
        }
    }

    /**
     * 当前类的构造函数执行之后执行此方法
     */
    @PostConstruct
    public void initFlowRules() {
        // 创建存放流控规则的集合
        List<FlowRule> rules = new ArrayList<>();
        // 创建流控规则
        FlowRule rule = new FlowRule();
        // 定义资源，表示Sentinel会对哪个资源生效
        rule.setResource(RESOURCE_NAME);
        // 定义流控规则的类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 定义QPS每秒能通过的请求数
        rule.setCount(2);
        // 将流控规则存放在集合中
        rules.add(rule);
        // 加载流控规则
        FlowRuleManager.loadRules(rules);
    }
}
