package com.turing.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@Slf4j
@RestController
public class EmpowerController {

    /**
     * 演示授权规则的使用
     */
    @GetMapping("/empower")
    public String empower() {
        return "success";
    }

}
