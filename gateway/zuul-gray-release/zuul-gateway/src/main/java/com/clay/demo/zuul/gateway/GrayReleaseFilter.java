package com.clay.demo.zuul.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 灰度发布过滤器
 */
@Configuration
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

        // 获取当前请求的 URI，比如 http://localhost:9000/order/get?xxxx
        String requestURI = request.getRequestURI();

        Map<String, GrayReleaseConfig> grayReleaseConfigs = grayReleaseConfigManager.getGrayReleaseConfigs();
        for (String path : grayReleaseConfigs.keySet()) {
            if (requestURI.contains(path)) {
                GrayReleaseConfig grayReleaseConfig = grayReleaseConfigs.get(path);
                if (grayReleaseConfig.getEnableGrayRelease() == 1) {
                    System.out.println("启用灰度发布功能, URI : " + requestURI);
                    return true;
                }
            }
        }

        System.out.println("不启用灰度发布功能, URI : " + requestURI);
        RibbonFilterContextHolder.getCurrentContext().add("version", "current");

        return false;
    }

    /**
     * 只有 shouldFilter() 返回 true 后，才会执行 run()
     */
    @Override
    public Object run() {
        activeGrayRelease();
        return null;
    }

    /**
     * 随机触发灰度发布
     */
    void randomGrayRelease() {
        Random random = new Random();
        int percent = random.nextInt(100);  //  [0, 99]

        // 命中 10% 灰度
        if (percent < 10) {
            RibbonFilterContextHolder.getCurrentContext().add("version", "newest");
        } else {
            RibbonFilterContextHolder.getCurrentContext().add("version", "current");
        }
    }

    /**
     * 根据请求参数主动触发灰度发布
     */
    void activeGrayRelease() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String gray = request.getParameter("gray");

        if ("true".equals(gray)) {
            RibbonFilterContextHolder.getCurrentContext().add("version", "newest");
        } else {
            RibbonFilterContextHolder.getCurrentContext().add("version", "current");
        }
    }

}