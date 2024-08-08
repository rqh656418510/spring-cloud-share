package com.clay.boot;

import com.clay.boot.config.OssConfiguration;
import com.clay.boot.config.SecurityConfiguration;
import com.clay.boot.config.WebConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author clay
 */
@Slf4j
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
            .main(MainApplication.class)
            .sources(MainApplication.class)
            // 关闭Banner（此方式的配置优先级较低，低于配置文件）
            .bannerMode(Banner.Mode.CONSOLE).run(args);

        // 验证Profiles环境隔离
        SecurityConfiguration securityConfiguration = getBean(context, SecurityConfiguration.class);
        WebConfiguration webConfiguration = getBean(context, WebConfiguration.class);
        OssConfiguration ossConfiguration = getBean(context, OssConfiguration.class);
        log.info("security configuration: {}", securityConfiguration);
        log.info("web configuration: {}", webConfiguration);
        log.info("oss configuration: {}", ossConfiguration);
    }

    public static <T> T getBean(ApplicationContext context, Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }

}