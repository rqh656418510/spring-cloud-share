package com.clay.scene.task;

import cn.hutool.core.util.RandomUtil;
import com.clay.scene.constant.Constants;
import com.clay.scene.entity.Barrage;
import com.clay.scene.service.BarrageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class BarrageTaskService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private BarrageService barrageService;

    /**
     * 模拟直播间的弹幕数据
     */
    @PostConstruct
    public void init() {
        log.info("初始化直播间的弹幕数据...");

        // 启动一个线程，模拟直播间内不同用户发送弹幕，在分布式系统中，建议用 XXL-JOB 来实现定时任务的调度执行
        new Thread(() -> {
            AtomicInteger atomicInteger = new AtomicInteger();

            while (true) {
                if (atomicInteger.get() == 100) {
                    break;
                }

                // 模拟房间号为 100 的直播间的弹幕数据，拼接 Redis 的 Key，比如 room:100
                long roomId = 100;
                String roomKey = Constants.ROOM_KEY + roomId;
                Random random = new Random();

                // 模拟用户发送弹幕，每 5 秒生成一批弹幕数据
                for (int i = 1; i <= 5; i++) {
                    Barrage barrage = new Barrage();
                    barrage.setCreateTime(new Date());
                    barrage.setRoomId(roomId);

                    long userId = random.nextLong(100000) + 1;
                    barrage.setUserId(userId);

                    int temp = random.nextInt(30) + 1;
                    String content = "发送弹幕: " + temp + "\t" + RandomUtil.randomString(temp);
                    barrage.setContent(content);

                    // 将弹幕数据保存到 MySQL（可选操作）
                    barrageService.add(barrage);

                    long timestamp = System.currentTimeMillis() / 1000;

                    // 将弹幕数据写入 Redis，对应的 Redis 命令，zadd room:100 time content
                    redisTemplate.opsForZSet().add(roomKey, barrage, timestamp);
                    // 设置弹幕数据的过期时间
                    redisTemplate.expire(roomKey, 24, TimeUnit.HOURS);

                    log.info("模拟房间号为 100 的直播间的弹幕数据： {}", barrage);
                }

                try {
                    // 模拟用户发送弹幕，每 5 秒生成一批弹幕数据
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                atomicInteger.getAndIncrement();
                System.out.println();
            }
        }, "Init_Live_Data").start();
    }

}
