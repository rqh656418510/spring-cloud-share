package com.clay.zuul.gateway.filter;

import com.clay.zuul.gateway.domain.GrayReleaseConfig;
import com.clay.zuul.gateway.task.GrayReleaseConfigManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 灰度发布过滤器
 */
@Component
public class GrayReleaseFilter extends ZuulFilter {

    @Resource
    private GrayReleaseConfigManager grayReleaseConfigManager;

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 获取当前请求的 URI，比如 /wms/inventory/deduct
        String requestURI = request.getRequestURI();

        Map<String, GrayReleaseConfig> grayReleaseConfigs = grayReleaseConfigManager.getGrayReleaseConfigs();
        for (String path : grayReleaseConfigs.keySet()) {
            // 匹配 URI，获取对应的网关灰度发布配置（可按实际需要更改匹配策略）
            if (requestURI.contains(path)) {
                GrayReleaseConfig grayReleaseConfig = grayReleaseConfigs.get(path);
                if (grayReleaseConfig.getEnableGrayRelease() == 1) {
                    System.out.println("启用灰度发布功能, URI : " + requestURI);
                    return true;
                }
            }
        }

        System.out.println("不启用灰度发布功能, URI : " + requestURI);

        // 不启用灰度发布时，默认会将请求轮询分发到对应的多个服务，包括标记为 newest 的服务（新服务）
        // 不启用灰度发布时，如果希望将请求只转发到标记为 current 的服务（旧服务），可以取消注释以下代码
        // RibbonFilterContextHolder.getCurrentContext().add("version", "current");

        return false;
    }

    /**
     * 只有 shouldFilter() 返回 true 后，才会执行 run()
     */
    @Override
    public Object run() {
        activeGrayRelease();
        // randomGrayRelease();
        return null;
    }

    /**
     * 随机触发灰度发布
     */
    void randomGrayRelease() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Random random = new Random();
        int percent = random.nextInt(100);  //  [0, 99]

        // 指定灰度的概率为 10%
        if (percent < 10) {
            // 定制 Ribbon 的负载均衡策略，按标签筛选择服务实例
            RibbonFilterContextHolder.getCurrentContext().add("version", "newest");
            // Header 透传，让灰度标记贯穿整个调用链路
            ctx.addZuulRequestHeader("version", "newest");
        } else {
            // 定制 Ribbon 的负载均衡策略，按标签筛选择服务实例
            RibbonFilterContextHolder.getCurrentContext().add("version", "current");
            // Header 透传，让灰度标记贯穿整个调用链路
            ctx.addZuulRequestHeader("version", "current");
        }
    }

    /**
     * 根据请求参数主动触发灰度发布
     */
    void activeGrayRelease() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String gray = request.getParameter("gray");

        // 根据请求参数决定是否灰度
        if (StringUtils.isNotBlank(gray) && "true".equals(gray)) {
            // 定制 Ribbon 的负载均衡策略，按标签筛选择服务实例
            RibbonFilterContextHolder.getCurrentContext().add("version", "newest");
            // Header 透传，让灰度标记贯穿整个调用链路（传递给下游服务）
            ctx.addZuulRequestHeader("version", "newest");
        } else {
            // 定制 Ribbon 的负载均衡策略，按标签筛选择服务实例
            RibbonFilterContextHolder.getCurrentContext().add("version", "current");
            // Header 透传，让灰度标记贯穿整个调用链路（传递给下游服务）
            ctx.addZuulRequestHeader("version", "current");
        }
    }

}