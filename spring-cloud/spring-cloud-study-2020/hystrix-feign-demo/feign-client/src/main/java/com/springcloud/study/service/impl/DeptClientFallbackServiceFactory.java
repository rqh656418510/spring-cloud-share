package com.springcloud.study.service.impl;

import com.springcloud.study.service.DeptClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DeptClientFallbackServiceFactory implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {

            @Override
            public String getDept(String deptName) {
                return "the dept not exist in this system, please confirm deptName";
            }
        };
    }
}
