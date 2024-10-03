package com.springcloud.study.controller;

import com.springcloud.study.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptClientService clientService;

    @GetMapping("/get")
    public String get(String deptName) {
        return clientService.getDept(deptName);
    }

}
