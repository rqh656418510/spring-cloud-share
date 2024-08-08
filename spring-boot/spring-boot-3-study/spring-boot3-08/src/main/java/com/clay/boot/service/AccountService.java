package com.clay.boot.service;

import com.clay.boot.entity.User;
import com.clay.boot.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author clay
 */
@Slf4j
@Service
public class AccountService implements ApplicationListener<LoginSuccessEvent> {

    /**
     * 接收事件
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        User user = (User) event.getSource();
        addAccountScore(user);
    }

    /**
     * 增加用户积分
     *
     * @param user
     */
    public void addAccountScore(User user) {
        log.info("{} 加了 1 积分", user.getUsername());
    }

}
