package org.dromara.hmily.demo.springcloud.order.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("all")
public class FeignConfiguration {

   /* @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .requestInterceptor(new HmilyFeignInterceptor())
                .invocationHandlerFactory(invocationHandlerFactory());
    }

    @Bean
    public InvocationHandlerFactory invocationHandlerFactory() {
        return (target, dispatch) -> {
            HmilyFeignHandler handler = new HmilyFeignHandler();
            //handler.setTarget(target);
            handler.setHandlers(dispatch);
            return handler;
        };
    }

    @Bean
    Request.Options feignOptions() {
        return new Request.Options(5000, 5000);
    }

    @Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }*/

}