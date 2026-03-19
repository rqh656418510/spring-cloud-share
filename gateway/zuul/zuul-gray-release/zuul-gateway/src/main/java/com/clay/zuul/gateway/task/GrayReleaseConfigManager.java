package com.clay.zuul.gateway.task;

import com.clay.zuul.gateway.domain.GrayReleaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 网关灰度发布配置规则管理器
 */
@Component
@Configuration
@EnableScheduling
public class GrayReleaseConfigManager {

    private volatile Map<String, GrayReleaseConfig> grayReleaseConfigs = new ConcurrentHashMap<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedRate = 30 * 1000)
    private void refreshRoute() {
        // 获取最新的网关灰度发布配置规则
        List<GrayReleaseConfig> results = jdbcTemplate.query(
            "select * from gateway_gray_release_config",
            new BeanPropertyRowMapper<>(GrayReleaseConfig.class));

        Map<String, GrayReleaseConfig> newConfigs = new ConcurrentHashMap<>();
        for (GrayReleaseConfig config : results) {
            newConfigs.put(config.getPath(), config);
        }

        // 原子替换旧的数据
        grayReleaseConfigs = newConfigs;
    }

    public Map<String, GrayReleaseConfig> getGrayReleaseConfigs() {
        return grayReleaseConfigs;
    }

}