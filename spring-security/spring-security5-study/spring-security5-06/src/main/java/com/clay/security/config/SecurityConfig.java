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
            .antMatchers("/goodnight").hasRole("sale")            // 需要用户拥有 sale 角色才能访问对应的 URL
            .antMatchers("/goodbye").hasAuthority("system")         // 需要用户拥有 system 权限才能访问对应的 URL
            .antMatchers("/hello").hasAuthority("sale_product")         // 需要用户拥有 sale_product 权限才能访问对应的 URL
            .antMatchers("/", "/login.html", "/user/login").permitAll()     // 配置哪些 URL 不需要登录就可以直接访问
            .anyRequest().authenticated()      // 配置其他 URL 需要登录才能访问
            .and().csrf().disable();           // 关闭 CSRF 防护

        http.exceptionHandling().accessDeniedPage("/unauth.html");      // 配置 403 页面
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
