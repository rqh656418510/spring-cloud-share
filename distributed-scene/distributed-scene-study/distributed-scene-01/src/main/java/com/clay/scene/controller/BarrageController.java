package com.clay.scene.controller;

import com.clay.scene.entity.Barrage;
import com.clay.scene.service.BarrageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 获取特定直播间的最新弹幕数据
     * <p> 适用于用户首次进入直播间获取弹幕数据
     *
     * @param roomId 直播间 ID
     * @param userId 用户 ID
     * @return 弹幕数据
     */
    @GetMapping("/getRoomNewest")
    public List<Barrage> getRoomNewest(Long roomId, Long userId) {
        return barrageService.getRoomNewest(roomId, userId);
    }

    /**
     * 根据时间范围进行拉取特定直播间的弹幕数据
     * <p> 适用于用户持续观看直播时拉取弹幕数据
     *
     * @param roomId 直播间 ID
     * @param userId 用户 ID
     * @return 弹幕数据
     */
    @GetMapping("/pullRoomData")
    public List<Barrage> pullRoomData(Long roomId, Long userId) {
        return barrageService.pullRoomData(roomId, userId);
    }

}
