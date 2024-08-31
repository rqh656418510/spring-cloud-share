package com.clay.scene.service.impl;

import com.clay.scene.constant.Constants;
import com.clay.scene.entity.Barrage;
import com.clay.scene.mapper.BarrageMapper;
import com.clay.scene.service.BarrageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BarrageServiceImpl implements BarrageService {

    @Resource
    private BarrageMapper barrageMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void add(Barrage barrage) {
        barrageMapper.insertSelective(barrage);
    }

    @Override
    public List<Barrage> getRoomNewest(Long roomId, Long userId) {
        List<Barrage> list = new ArrayList<>();

        String roomKey = Constants.ROOM_KEY + roomId;

        // 获取指定直播间的最新 5 条弹幕数据，对应的 Redis 命令 "zrevrange room:100 0 4 withscores"
        Set<ZSetOperations.TypedTuple<Barrage>> set = this.redisTemplate.opsForZSet().reverseRangeWithScores(roomKey, 0, 4);
        for (ZSetOperations.TypedTuple<Barrage> item : set) {
            list.add(item.getValue());
        }

        // 将当前时间戳存入 Redis，提供给用户下次拉取弹幕数据使用，用于控制看过的弹幕不再拉取
        Long now = System.currentTimeMillis() / 1000;
        String userkey = Constants.ROOM_USER_TIME_KEY + userId;
        this.redisTemplate.opsForValue().set(userkey, now, 24, TimeUnit.HOURS);

        return list;
    }

    public List<Barrage> pullRoomData(Long roomId, Long userId) {
        List<Barrage> list = new ArrayList<>();

        String roomKey = Constants.ROOM_KEY + roomId;
        String userkey = Constants.ROOM_USER_TIME_KEY + userId;

        // 当前时间
        long now = System.currentTimeMillis() / 1000;

        // 获取用户上次拉取弹幕数据的时间
        Long lastTime = null;
        Object lastValue = this.redisTemplate.opsForValue().get(userkey);
        if (lastValue == null) {
            // 默认拉取 10 秒前的弹幕数据
            lastTime = now - 10;
        } else {
            lastTime = Long.parseLong(lastValue.toString());
        }

        // 获取从上次拉取到现在的弹幕数据，对应的 Redis 命令 "zrange room:100 1725093501 1725093606 withscores"
        Set<ZSetOperations.TypedTuple<Barrage>> set = this.redisTemplate.opsForZSet().rangeByScoreWithScores(roomKey, lastTime, now);
        for (ZSetOperations.TypedTuple<Barrage> item : set) {
            list.add(item.getValue());
        }

        // 将当前时间戳存入 Redis，提供给用户下次拉取弹幕数据使用，用于控制看过的弹幕不再拉取
        now = System.currentTimeMillis() / 1000;
        this.redisTemplate.opsForValue().set(userkey, now, 24, TimeUnit.HOURS);

        return list;
    }

}
