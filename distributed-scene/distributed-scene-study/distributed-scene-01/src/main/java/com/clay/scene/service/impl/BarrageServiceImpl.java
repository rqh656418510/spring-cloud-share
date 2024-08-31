package com.clay.scene.service.impl;

import com.clay.scene.entity.Barrage;
import com.clay.scene.mapper.BarrageMapper;
import com.clay.scene.service.BarrageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BarrageServiceImpl implements BarrageService {

    @Resource
    private BarrageMapper barrageMapper;

    @Override
    public void add(Barrage barrage) {
        barrageMapper.insertSelective(barrage);
    }

}
