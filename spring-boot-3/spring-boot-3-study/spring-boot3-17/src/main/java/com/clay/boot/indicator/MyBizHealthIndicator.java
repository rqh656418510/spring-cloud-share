package com.clay.boot.indicator;

import com.clay.boot.component.MyBizComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author clay
 */
@Component
public class MyBizHealthIndicator implements HealthIndicator {

    @Autowired
    private MyBizComponent bizComponent;

    @Override
    public Health health() {
        // 检查自定义组件是否存活
        boolean check = bizComponent.check();
        if (check) {
            // 存活
            return Health.up()
                .withDetail("msg", "Servcie online")
                .withDetail("code", "200")
                .build();
        } else {
            // 下线
            return Health.down()
                .withException(new RuntimeException())
                .withDetail("msg", "Servcie offline")
                .withDetail("code", "500")
                .build();
        }
    }

}
