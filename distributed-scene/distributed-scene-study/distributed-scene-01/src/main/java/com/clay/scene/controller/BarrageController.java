package com.clay.scene.controller;

import com.clay.scene.entity.Barrage;
import com.clay.scene.service.BarrageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barrage/")
public class BarrageController {

    @Resource
    private BarrageService barrageService;

    @PostMapping("/add")
    public String add(@RequestBody Barrage barrage) {
        barrageService.add(barrage);
        return "success";
    }

}
