package com.clay.wms.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Feign 拦截器
 */
public class GrayFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 从 Header 获取灰度发布的版本标记
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String version = request.getHeader("version");

        if (StringUtils.isNotBlank(version)) {
            // Header 透传，让灰度标记贯穿整个调用链路（传递给下游服务）
            template.header("version", version);
        }
    }

}