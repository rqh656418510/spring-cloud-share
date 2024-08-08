package com.clay.boot.controller;

import com.clay.boot.service.MyBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clay
 */
@RestController
public class MyBizController {

    @Autowired
    private MyBizService myBizService;

    @GetMapping("/process")
    public String process() {
        myBizService.process();
        return "success";
    }

}
