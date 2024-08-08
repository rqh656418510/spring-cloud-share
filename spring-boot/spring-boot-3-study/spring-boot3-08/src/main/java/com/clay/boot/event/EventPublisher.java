package com.clay.boot.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author clay
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * 发送事件
     * @param event
     */
    public void sendEvent(ApplicationEvent event) {
        this.eventPublisher.publishEvent(event);
    }

}
