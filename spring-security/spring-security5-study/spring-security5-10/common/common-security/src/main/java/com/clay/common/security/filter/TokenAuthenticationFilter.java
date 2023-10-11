package com.clay.common.security.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.clay.common.security.helper.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 授权过滤器
 *
 * @author clay
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 当前认证成功用户的权限信息
        UsernamePasswordAuthenticationToken authResult = getAuthentication(request);

        // 将用户的权限信息放到权限上下文中
        if (authResult != null) {
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        
        chain.doFilter(request, response);
    }

    /**
     * 获取认证成功用户的权限信息
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 从Header获取Token
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            // 根据Token获取用户信息
            String username = tokenManager.getUserInfoFromToken(token);

            // 从缓存中获取用户的权限列表
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(username);

            // 封装用户的权限列表
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(permissionValueList)) {
                for (String permissionValue : permissionValueList) {
                    if (StringUtils.isEmpty(permissionValue)) {
                        continue;
                    }
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                    authorities.add(authority);
                }
            }
            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        return null;
    }

}
