package com.springcloud.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

public class PostFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecondPreFilter.class);

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("==> custom zuul post filter");
        //从RequestContext获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //处理返回中文乱码
        context.getResponse().setCharacterEncoding("UTF-8");
        //获取上下文中保存的responseBody
        String responseBody = context.getResponseBody();
        //如果responseBody不为空，则说明流程有异常发生
        if (null != responseBody) {
            //设定返回状态码
            context.setResponseStatusCode(500);
            //替换响应报文
            context.setResponseBody(responseBody);
        }
        return null;
    }
}
