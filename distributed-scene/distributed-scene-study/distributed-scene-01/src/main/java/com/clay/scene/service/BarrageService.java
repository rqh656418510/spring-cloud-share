package com.clay.scene.service;

import com.clay.scene.entity.Barrage;

import java.util.List;

public interface BarrageService {

    /**
     * 添加弹幕数据
     */
    void add(Barrage barrage);

    /**
     * 获取特定直播间的最新弹幕数据
     * <p> 适用于用户首次进入直播间获取弹幕数据
     *
     * @param roomId 直播间 ID
     * @param userId 用户 ID
     * @return 弹幕数据
     */
    List<Barrage> getRoomNewest(Long roomId, Long userId);

    /**
     * 根据时间范围拉取特定直播间的弹幕数据
     * <p> 适用于用户持续观看直播时拉取弹幕数据
     *
     * @param roomId 直播间 ID
     * @param userId 用户 ID
     * @return 弹幕数据
     */
    List<Barrage> pullRoomData(Long roomId, Long userId);

}
