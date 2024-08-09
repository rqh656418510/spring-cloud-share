package com.clay.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 3 的配置类
 * <p> 浏览器访问地址：http://127.0.0.1:8001/swagger-ui/index.html
 *
 * @author clay
 * @version 1.0
 */
@Configuration
public class Swagger3Config {

    @Bean
    public GroupedOpenApi PayApi() {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi OtherApi() {
        return GroupedOpenApi.builder().group("其它微服务模块").pathsToMatch("/other/**", "/others").build();
    }

    @Bean
    public GroupedOpenApi CustomerApi() {
        return GroupedOpenApi.builder().group("客户微服务模块").pathsToMatch("/customer/**", "/customers").build();
    }

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI().info(new Info().title("Spring Cloud 2024").description("API 文档").version("v1.0"))
            .externalDocs(new ExternalDocumentation().description("www.example.com").url("https://www.example.com/"));
    }

}
