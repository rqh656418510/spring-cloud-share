package com.springcloud.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class ThirdPreFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ThirdPreFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return (boolean) context.get("logic-is-success");
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("==> third custom zuul pre filter");
        //从RequestContext获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = context.getRequest();
        //从request尝试获取b参数值
        String b = request.getParameter("b");
        if (null == b) {
            //对该请求禁止路由，也就是禁止访问下游服务
            context.setSendZuulResponse(false);
            //保存于上下文，作为同类型下游Filter的执行开关，假定后续还有自定义Filter当设置此值
            context.set("logic-is-success", false);
            //设定responseBody供PostFilter使用
            context.setResponseBody("{\"status\":500,\"message\":\"param b is null !\"}");
            return null;
        }
        //设置避免报空异常
        context.set("logic-is-success", true);
        return null;
    }
}
