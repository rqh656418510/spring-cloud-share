package com.clay.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author clay
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/login.html")           // 配置哪个 URL 为登录页面
            .loginProcessingUrl("/user/login")  // 配置哪个为登录接口的 URL
            .defaultSuccessUrl("/hello")        // 配置登录成功之后跳转到哪个 URL
            .and().authorizeRequests()
            .antMatchers("/hello").hasAuthority("manager")        // 需要用户拥有 manager 权限才能访问对应的 URL
            .antMatchers("/", "/login.html", "/user/login").permitAll()     // 配置哪些 URL 不需要登录就可以直接访问
            .anyRequest().authenticated()      // 配置其他 URL 需要登录才能访问
            .and().csrf().disable();           // 关闭 CSRF 防护
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
