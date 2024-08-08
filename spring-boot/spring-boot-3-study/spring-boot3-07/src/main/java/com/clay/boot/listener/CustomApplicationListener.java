package com.clay.boot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author clay
 */
@Slf4j
public class CustomApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 接收所有事件
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("==> Event {}", event);
    }

}
