package com.clay.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author clay
 */
@Configuration
public class WebSecurityConfiguration {

    /**
     * 请求认证
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> {
            registry
                .requestMatchers(("/")).permitAll()         // 首页支持所有人访问
                .anyRequest().authenticated();              // 其他任意请求都需要认证登录
        });

        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.loginPage("/login").permitAll();    // 自定义登录页面，且所有人都可以访问登录页面
        });

        return http.build();
    }

    /**
     * 自定义用户信息
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("admin")
            .password(passwordEncoder.encode("123456"))
            .roles("admin", "hr")
            .authorities("file_read", "file_write")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

}
