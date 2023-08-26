package com.clay.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author clay
 */
@Configuration
public class WebSecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // 请求认证
        http.authorizeHttpRequests(registry -> {
            registry
                .requestMatchers(("/")).permitAll()         // 首页支持所有人访问
                .anyRequest().authenticated();              // 其他任意请求都需要认证登录
        });

        // 表单登录
        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.loginPage("/login").permitAll();    // 自定义登录页面，且所有人都可以访问登录页面
        });

        return http.build();
    }

}
