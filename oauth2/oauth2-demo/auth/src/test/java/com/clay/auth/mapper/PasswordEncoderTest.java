package com.clay.auth.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encoderPassword() {
        String password = passwordEncoder.encode("123");
        System.out.println(password);
    }

    @Test
    public void encoderSecert() {
        String secret = passwordEncoder.encode("secret");
        System.out.println(secret);
    }

}
