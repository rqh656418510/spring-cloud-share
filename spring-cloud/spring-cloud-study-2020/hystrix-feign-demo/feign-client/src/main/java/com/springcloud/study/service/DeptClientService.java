package com.springcloud.study.service;

import com.springcloud.study.service.impl.DeptClientFallbackServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PROVIDER", fallbackFactory = DeptClientFallbackServiceFactory.class)
public interface DeptClientService {

    @RequestMapping("/dept/getDept")
    public String getDept(@RequestParam("deptName") String deptName);

}