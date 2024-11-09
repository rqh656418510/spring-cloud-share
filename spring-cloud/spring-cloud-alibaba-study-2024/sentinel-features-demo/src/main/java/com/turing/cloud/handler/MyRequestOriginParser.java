package com.turing.cloud.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 自定义请求来源转换器
 *
 * @author turing
 * @version 1.0
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 指定从哪里获取请求来源，比如可以从 HTTP 请求的参数、Header 获取
        return httpServletRequest.getParameter("serverName");
    }

}
