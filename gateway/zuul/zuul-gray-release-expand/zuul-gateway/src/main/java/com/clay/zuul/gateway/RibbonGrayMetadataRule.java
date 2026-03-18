package com.clay.zuul.gateway;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

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

        // 获取当前请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        String version = ctx.getZuulRequestHeaders().get("version");

        // 匹配的服务实例列表
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
            return servers.get(new Random().nextInt(servers.size()));
        }

        return matched.get(new Random().nextInt(matched.size()));
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}