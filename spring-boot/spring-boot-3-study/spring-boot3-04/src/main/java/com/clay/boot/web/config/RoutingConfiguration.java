package com.clay.boot.web.config;

import com.clay.boot.web.biz.UserBizHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * 路由信息配置
 *
 * @author clay
 */
@Configuration
public class RoutingConfiguration {

    private static final RequestPredicate ACCEPT_ALL = RequestPredicates.accept(MediaType.ALL);
    private static final RequestPredicate ACCEPT_JSON = RequestPredicates.accept(MediaType.APPLICATION_JSON);

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserBizHandler userBizHandler) {
        return RouterFunctions.route()
            .GET("/user/{id}", ACCEPT_ALL, userBizHandler::getUser)
            .GET("/users", ACCEPT_ALL, userBizHandler::listUser)
            .POST("/user", ACCEPT_JSON, userBizHandler::addUser)
            .PUT("/user/{id}", ACCEPT_JSON, userBizHandler::updateUser)
            .DELETE("/user/{id}", ACCEPT_ALL, userBizHandler::deleteUser)
            .build();
    }

}
