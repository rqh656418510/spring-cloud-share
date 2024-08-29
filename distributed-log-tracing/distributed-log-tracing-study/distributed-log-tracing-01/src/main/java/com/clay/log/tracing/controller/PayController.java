package com.clay.log.tracing.controller;

import com.clay.log.tracing.annotations.MethodExporter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
public class PayController {

    @MethodExporter
    @GetMapping(value = "/list")
    public Map list(@RequestParam(value = "page", defaultValue = "1") int page,
                    @RequestParam(value = "rows", defaultValue = "5") int rows) {

        Map<String, String> result = new LinkedHashMap<>();
        result.put("code", "200");
        result.put("message", "success");

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @MethodExporter
    @GetMapping(value = "/get")
    public Map get() {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("code", "404");
        result.put("message", "not-found");

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @GetMapping(value = "/update")
    public String update() {
        System.out.println("invoke method without @MethodExporter");
        return "success";
    }

}
