package com.springcloud.study.controller;

import com.springcloud.study.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/get")
    public String get(String deptName) {
        deptService.getDept("IT");
        deptService.getDept("IT");
        return "annotation cache test finished";
    }

    @GetMapping("/find")
    public String find(String deptName) {
        // 调用接口并缓存数据
        deptService.findDept("IT");
        deptService.findDept("IT");
        // 清除缓存
        deptService.updateDept(deptName);
        // 再调用接口
        deptService.findDept("IT");
        deptService.findDept("IT");
        return "annotation cache test finished";
    }

}
