package com.clay.auth.security.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.clay.common.entity.Permission;
import com.clay.common.entity.User;
import com.clay.common.service.PermissionService;
import com.clay.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author clay
 * @version 1.0
 */
@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 封装登录用户的信息
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 获取用户的所有权限
        List<Permission> permissions = permissionService.getPermissionsByUserId(user.getId());
        List<String> codes = permissions.stream().map(Permission::getCode).collect(Collectors.toList());
        String[] authorities = null;
        if (CollectionUtils.isNotEmpty(codes)) {
            authorities = new String[codes.size()];
            codes.toArray(authorities);
        }
        // 隐藏用户密码
        String password = user.getPassword();
        user.setPassword(null);
        // 身份令牌
        String principal = JSON.toJSONString(user);
        return org.springframework.security.core.userdetails.User.withUsername(principal).password(password).authorities(authorities).build();
    }

}
