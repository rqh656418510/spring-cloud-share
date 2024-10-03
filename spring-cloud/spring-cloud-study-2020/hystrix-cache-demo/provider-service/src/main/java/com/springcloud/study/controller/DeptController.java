package com.springcloud.study.controller;

import com.springcloud.study.domain.Dept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @GetMapping("/getDept")
    public Dept getDept(String deptName) {
        Dept dept = new Dept(1L, deptName);
        return dept;
    }

}
