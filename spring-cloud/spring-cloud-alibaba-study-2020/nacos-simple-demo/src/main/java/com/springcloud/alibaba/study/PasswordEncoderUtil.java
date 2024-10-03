package com.springcloud.alibaba.study;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author clay
 */
public class PasswordEncoderUtil {

    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }
}
