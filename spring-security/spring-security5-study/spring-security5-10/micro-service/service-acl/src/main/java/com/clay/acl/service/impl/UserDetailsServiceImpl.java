package com.clay.acl.service.impl;

import com.clay.acl.entity.User;
import com.clay.acl.service.PermissionService;
import com.clay.acl.service.UserService;
import com.clay.common.security.model.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security 用户信息服务
 *
 * @author clay
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * Spring Security 加载用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询数据
        User user = userService.selectByUsername(username);
        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        com.clay.common.security.entity.User curUser = new com.clay.common.security.entity.User();
        BeanUtils.copyProperties(user, curUser);

        // 获取用户的权限列表
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(curUser);
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }

}
