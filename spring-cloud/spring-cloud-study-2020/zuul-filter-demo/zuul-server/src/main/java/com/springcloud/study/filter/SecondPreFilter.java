package com.springcloud.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class SecondPreFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecondPreFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("==> second custom zuul pre filter");
        //从RequestContext获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = context.getRequest();
        //从request尝试获取a参数值
        String a = request.getParameter("a");
        if (null == a) {
            //对该请求禁止路由，也就是禁止访问下游服务
            context.setSendZuulResponse(false);
            //保存于上下文，作为同类型下游Filter的执行开关
            context.set("logic-is-success", false);
            //设定responseBody供PostFilter使用
            context.setResponseBody("{\"status\":500,\"message\":\"param a is null !\"}");
            return null;
        }
        //设置避免报空异常
        context.set("logic-is-success", true);
        return null;
    }
}
