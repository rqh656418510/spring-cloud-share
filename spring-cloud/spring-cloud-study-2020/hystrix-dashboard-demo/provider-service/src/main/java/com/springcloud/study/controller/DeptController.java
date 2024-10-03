package com.springcloud.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @GetMapping("/getDept")
    @HystrixCommand(fallbackMethod = "defaultDept")
    public String getDept(String deptName) {
        if (deptName.equals("IT")) {
            return "this is real dept";
        } else {
            throw new RuntimeException("dept is not exist");
        }
    }

    public String defaultDept(String deptName) {
        return "the dept not exist in this system";
    }

}
