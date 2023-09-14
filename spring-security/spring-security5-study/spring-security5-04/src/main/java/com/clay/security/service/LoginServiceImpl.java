package com.clay.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clay.security.entity.User;
import com.clay.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        // 查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User name not exist");
        }
        List<GrantedAuthority> authors = AuthorityUtils.commaSeparatedStringToAuthorityList("manager");
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authors);
    }

}
