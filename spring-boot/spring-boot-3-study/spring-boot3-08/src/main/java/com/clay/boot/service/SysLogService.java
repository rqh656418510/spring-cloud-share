package com.clay.boot.service;

import com.clay.boot.entity.User;
import com.clay.boot.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author clay
 */
@Slf4j
@Service
public class SysLogService {

    /**
     * 接收事件
     *
     * @param event
     */
    @Order(2)
    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        User user = (User) event.getSource();
        sendCoupon(user);
    }

    /**
     * 记录系统日志
     *
     * @param user
     */
    public void sendCoupon(User user) {
        log.info("{} 成功登录系统", user.getUsername());
    }

}
