package com.clay.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clay.security.entity.Permission;
import com.clay.security.entity.Role;
import com.clay.security.entity.User;
import com.clay.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 */
@Service("userDetailsService")
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User name not exist");
        }

        // 查询用户角色
        List<Role> roleList = userMapper.selectRoleByUserId(userEntity.getId());

        // 查询用户权限
        List<Permission> permissionList = userMapper.selectPermissionByUserId(userEntity.getId());

        // 声明一个角色权限集合
        List<GrantedAuthority> authorList = new ArrayList<>();

        // 处理角色
        roleList.forEach(role -> {
            authorList.add(new SimpleGrantedAuthority(role.getCode()));
        });

        // 处理权限
        permissionList.forEach(permission -> {
            authorList.add(new SimpleGrantedAuthority(permission.getCode()));
        });

        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
            userEntity.getPassword(), authorList);
    }

}
