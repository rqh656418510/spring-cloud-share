package com.clay.boot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clay
 */
@Configuration
public class ApiUiConfiguration {

    /**
     * 分组设置
     *
     * @return
     */
    @Bean
    public GroupedOpenApi empApi() {
        return GroupedOpenApi.builder().group("员工管理").pathsToMatch("/emp/**", "/emps").build();
    }

    /**
     * 分组设置
     *
     * @return
     */
    @Bean
    public GroupedOpenApi deptApi() {
        return GroupedOpenApi.builder().group("部门管理").pathsToMatch("/dept/**", "/depts").build();
    }

    /**
     * OpenAPI 配置
     *
     * @return
     */
    @Bean
    public OpenAPI docsOpenAPI() {
        return new OpenAPI().info(
            new Info().title("SpringBoot3 CRUD API").description("CRUD 接口文档").version("V0.0.1")
                .license(new License().name("Apache 2.0").url("https://springdoc.org"))).externalDocs(
            new ExternalDocumentation().description("Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));
    }

}
