package com.clay.common.base.utils;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author clay
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Api")
            .apiInfo(webApiInfo())
            .select()
            //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            .build();

    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
            .title("API文档")
            .description("本文档描述了微服务接口的定义")
            .version("1.0")
            .contact(new Contact("java", "http://java.com", "java@gmail.com"))
            .build();
    }

}
