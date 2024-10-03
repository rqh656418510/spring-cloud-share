package com.springcloud.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @RequestMapping("/getDept")
    public String getDept(String deptName) {
        throw new RuntimeException("dept is not exist");
    }

}
