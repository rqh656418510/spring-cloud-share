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
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author clay
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersistentTokenRepository tokenRepository;

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
            .and()
            .authorizeRequests().antMatchers("/", "/login.html", "/user/login").permitAll()     // 配置哪些 URL 不需要登录就可以直接访问
            .anyRequest().authenticated()      // 配置其他 URL 需要登录才能访问
            .and().csrf().disable();           // 关闭 CSRF 防护

        // 配置自动登录功能
        http.rememberMe()
            .tokenRepository(tokenRepository)
            .tokenValiditySeconds(60)       // 配置Token的有效时间，单位秒
            .userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
