package com.springcloud.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取配置信息
 */
@Component
@ConfigurationProperties(prefix = ConfigSupportProperties.CONFIG_PREFIX)
public class ConfigSupportProperties {

    public static final String CONFIG_PREFIX = "spring.cloud.config.backup";
    private final String DEFAULT_FILE_NAME = "fallback.properties";
    private boolean enable = false;
    private String fallbackLocation;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getFallbackLocation() {
        return fallbackLocation;
    }

    public void setFallbackLocation(String fallbackLocation) {
        // 如果只是填写路径， 就添加上一个默认的文件名
        if (fallbackLocation.indexOf(".") == -1) {
            this.fallbackLocation = fallbackLocation + DEFAULT_FILE_NAME;
            return;
        }
        this.fallbackLocation = fallbackLocation;
    }
}
