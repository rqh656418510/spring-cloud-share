package com.clay.boot.config;

import com.clay.boot.api.WeatherApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author clay
 */
@Configuration
public class AliYunApiConfiguration {

    @Value("${aliyun.appcode:}")
    private String appCode;

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory() {
        // 创建客户端
        WebClient client = WebClient.builder()
            .defaultHeader("Authorization", "APPCODE " + appCode)
            .codecs(clientCodecConfigurer -> {
                // 响应数据量太大有可能会超出缓冲区，所以这里设置的大一点
                clientCodecConfigurer.defaultCodecs().maxInMemorySize(256 * 1024 * 1024);
            }).build();

        // 创建代理工厂
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
    }

    @Bean
    public WeatherApi weatherApi(HttpServiceProxyFactory httpServiceProxyFactory) {
        // 获取代理对象
        return httpServiceProxyFactory.createClient(WeatherApi.class);
    }

}
