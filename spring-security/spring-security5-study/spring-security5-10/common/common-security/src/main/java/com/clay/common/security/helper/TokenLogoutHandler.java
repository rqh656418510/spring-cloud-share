package com.clay.common.security.helper;

import cn.hutool.core.util.StrUtil;
import com.clay.common.base.utils.utils.R;
import com.clay.common.base.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录处理器
 *
 * @author clay
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            String username = tokenManager.getUserInfoFromToken(token);
            // 删除缓存数据
            if (StrUtil.isNotBlank(username)) {
                redisTemplate.delete(username);
            }
        }
        ResponseUtil.out(response, R.ok());
    }

}
