package com.clay.wms.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ribbon 负载均衡策略
 */
public class RibbonGrayMetadataRule extends AbstractLoadBalancerRule {

    /**
     * 必须有无参构造方法
     */
    public RibbonGrayMetadataRule() {

    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = getLoadBalancer();
        List<Server> servers = lb.getAllServers();

        // 从 Header 获取灰度发布的版本标记
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String version = request.getHeader("version");

        // 匹配的服务实例列表（根据 Eureka 的 metadata 过滤服务实例）
        List<Server> matched = new ArrayList<>();
        for (Server server : servers) {
            if (server instanceof DiscoveryEnabledServer) {
                DiscoveryEnabledServer ds = (DiscoveryEnabledServer) server;
                String metaVersion = ds.getInstanceInfo().getMetadata().get("version");
                if (StringUtils.isNotBlank(version) && version.equals(metaVersion)) {
                    matched.add(server);
                }
            }
        }

        // Fallback 处理
        if (matched.isEmpty()) {
            // 随机负载均衡算法
            return servers.get(new Random().nextInt(servers.size()));
        }

        // 随机负载均衡算法
        return matched.get(new Random().nextInt(matched.size()));
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}