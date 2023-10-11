package com.clay.common.security.helper;

import com.clay.common.base.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码处理器
 *
 * @author clay
 */
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int length) {

    }

    @Override
    public String encode(CharSequence rawPassword) {
        // MD5 加密
        return MD5.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }

}
