package com.clay.boot.event;

import com.clay.boot.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author clay
 */
public class LoginSuccessEvent extends ApplicationEvent {

    public LoginSuccessEvent(User user) {
        super(user);
    }

}
