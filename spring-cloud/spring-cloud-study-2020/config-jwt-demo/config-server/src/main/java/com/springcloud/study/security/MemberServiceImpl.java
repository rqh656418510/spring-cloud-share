package com.springcloud.study.security;

import com.springcloud.study.model.JwtUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements UserDetailsService {

    private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();

    @Value("${spring.security.user.name}")
    private String hardcodedUser;

    @Value("${spring.security.user.password}")
    private String password;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 对密码进行加密
        String hardcodedPassword = BCRYPT.encode(password);
        if (username.equals(hardcodedUser) == false) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
            grantedAuthorityList.add(simpleGrantedAuthority);
            return new JwtUser(hardcodedUser, hardcodedPassword, grantedAuthorityList);
        }
    }
}
