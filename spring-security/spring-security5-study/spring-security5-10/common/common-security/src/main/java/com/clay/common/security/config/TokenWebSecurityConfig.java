package com.clay.common.security.config;

import com.clay.common.security.filter.TokenAuthenticationFilter;
import com.clay.common.security.filter.TokenLoginFilter;
import com.clay.common.security.helper.DefaultPasswordEncoder;
import com.clay.common.security.helper.TokenLogoutHandler;
import com.clay.common.security.helper.TokenManager;
import com.clay.common.security.helper.UnauthorizedEncryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 安全配置
 *
 * @author clay
 */
@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private UserDetailsService userDetailsService;
    private DefaultPasswordEncoder passwordEncoder;

    public TokenWebSecurityConfig(TokenManager tokenManager, RedisTemplate redisTemplate, UserDetailsService userDetailsService, DefaultPasswordEncoder passwordEncoder) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
            .authenticationEntryPoint(new UnauthorizedEncryPoint())     // 配置未授权处理器
            .and().csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and().logout().logoutUrl("/admin/acl/index/logout")    // 配置退出登录的  URL
            .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()     // 配置退出登录处理器
            .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))      // 配置认证过滤器
            .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();    // 配置授权过滤器
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置UserDetailsService和密码解析器
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置哪些路径可以不进行认证，可以直接访问
        web.ignoring().antMatchers("/api/**");
    }

}
