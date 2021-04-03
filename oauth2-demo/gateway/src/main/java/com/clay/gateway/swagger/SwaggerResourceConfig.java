/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.clay.gateway.swagger;

import com.clay.common.constants.AuthConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 聚合各个服务的Swagger2接口
 *
 * @author clay
 * @version 1.0
 */
@Component
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    /**
     * 服务实例ID的格式
     */
    @Value("${spring.cloud.gateway.discovery.locator.lower-case-service-id:false}")
    private boolean lowerCaseServiceId;

    /**
     * Swagger2聚合文档排除服务实例的ID<br>
     * 区分英文大小写，多个ID之间可以使用英文逗号隔开<br>
     */
    @Value("${spring.cloud.gateway.swagger.exclude:}")
    private String swaggerExclude;

    /**
     * 网关路由
     */
    private final RouteLocator routeLocator;

    /**
     * 网关应用的名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    public SwaggerResourceConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    /**
     * 获取Swagger2的资源列表<br>
     * 资源的格式：/serviceId/v2/api-doc
     *
     * @return
     */
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routeHosts = new ArrayList<>();
        // 由于网关采用的是负载均衡的方式，因此需要拿到所有应用的host：serviceId
        routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
                // 将Gateway服务自身排除掉，让Swagger2的UI界面不再显示Gateway的服务节点
                .filter(route -> !applicationName.equalsIgnoreCase(route.getUri().getHost()))
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));

        // 记录已经添加过的Server，存在同一个应用注册了多个服务在注册中心上
        Set<String> dealed = new HashSet<>();
        Set<String> excluded = swaggerExcludeServiceId();
        routeHosts.forEach(serviceId -> {
            // 拼接url，样式为/serviceId/v2/api-doc，当网关调用接口时，会自动通过负载均衡寻找对应的主机
            serviceId = lowerCaseServiceId ? serviceId.toLowerCase() : serviceId;
            String url = "/" + serviceId + AuthConstants.API_SWAGGER2;
            if (!dealed.contains(url) && !excluded.contains(serviceId)) {
                dealed.add(url);
                resources.add(swaggerResource(serviceId, url));
            }
        });
        return resources;
    }

    /**
     * 获取Swagger2聚合文档排除的服务实例的ID
     *
     * @return
     */
    private Set<String> swaggerExcludeServiceId() {
        Set<String> resultSet = new HashSet<>();
        if (StringUtils.isNotBlank(swaggerExclude)) {
            String[] excludeArray = swaggerExclude.split(",");
            for (String serviceId : excludeArray) {
                if (lowerCaseServiceId) {
                    resultSet.add(serviceId.trim().toLowerCase());
                } else {
                    resultSet.add(serviceId.trim().toUpperCase());
                }
            }
        }
        return resultSet;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setUrl(location);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
