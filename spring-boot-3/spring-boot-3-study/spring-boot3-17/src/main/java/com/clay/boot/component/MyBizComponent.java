package com.clay.boot.component;

import org.springframework.stereotype.Component;

@Component
public class MyBizComponent {

    /**
     * 检查自定义组件的健康状态
     *
     * @return
     */
    public boolean check() {
        // 业务代码判断当前组件是否存活
        return true;
    }

}
