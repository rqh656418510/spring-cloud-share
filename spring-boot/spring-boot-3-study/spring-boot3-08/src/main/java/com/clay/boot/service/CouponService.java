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
public class CouponService {

    /**
     * 接收事件
     *
     * @param event
     */
    @Order(1)
    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        User user = (User) event.getSource();
        sendCoupon(user);
    }

    /**
     * 发送优惠券
     *
     * @param user
     */
    public void sendCoupon(User user) {
        log.info("{} 得到 1 张优惠券", user.getUsername());
    }

}
