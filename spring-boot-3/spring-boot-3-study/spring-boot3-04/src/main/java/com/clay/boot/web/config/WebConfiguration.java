package com.clay.boot.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * @author clay
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 保留默认的配置规则，并添加新的配置规则
        registry
            // 自定义静态资源访问路径的前缀
            .addResourceHandler("/static/**")
            // 自定义静态资源文件夹的位置
            .addResourceLocations("classpath:/static/", "classpath:/public/", "classpath:/asset/")
            // 自定义HTTP缓存控制
            .setCacheControl(CacheControl.maxAge(7200, TimeUnit.SECONDS));
    }

}
