package com.sentinel.study.config;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.zookeeper.ZookeeperDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author clay
 */
@Configuration
public class SentinelZookeeperConfig {

    public static final String ZOOKEEPER_ADDRESS = "127.0.0.1:2181";

    public static final String ZOOKEEPER_PATH = "/Sentinel/FlowRules";

    /**
     * Sentinel从Zookeeper加载规则配置数据
     */
    @PostConstruct
    public void init() {
        // 参数一：Zookeeper的地址
        // 参数二：Zookeeper中数据的路径
        // 参数三：Zookeeper中数据的解析器
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ZookeeperDataSource<>(
                ZOOKEEPER_ADDRESS,
                ZOOKEEPER_PATH,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));

        // 加载流控规则
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
