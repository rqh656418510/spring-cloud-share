package com.clay.auth.security.config;

import com.clay.auth.security.handler.LoginFailureHandler;
import com.clay.auth.security.handler.LoginSuccessHandler;
import com.clay.auth.security.handler.LogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 站点安全配置
 *
 * @author clay
 * @version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private LoginFailureHandler failureHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 放行所有接口，主要包括但不限以下接口
        // Swagger2的接口：/v2/api-docs
        // Oauth2.0的接口：/oauth/**, /login
        // Spring Boot Actuator的接口：/actuator/**
        http.csrf().disable().formLogin()
                .loginProcessingUrl("/login").permitAll()
                .successHandler(successHandler).permitAll()
                .failureHandler(failureHandler).permitAll().and()
                .logout().logoutSuccessHandler(logoutHandler).and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }

}
