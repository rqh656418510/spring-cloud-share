package com.clay.shop.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clay
 * @version 1.0
 */
@Configuration
@EnableBeanValidator
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clay"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Knife4j 接口文档")
                .description("业务接口 API 文档.")
                .termsOfServiceUrl("")
                .version("v1.0.0")
                .build();
    }

    /**
     * Swagger2 认证的安全上下文
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        List<AuthorizationScope> scopes = new ArrayList<>();
        SecurityReference securityReference = new SecurityReference("oauth2", scopes.toArray(new AuthorizationScope[]{}));
        SecurityContext securityContext = new SecurityContext(Lists.newArrayList(securityReference), PathSelectors.ant("/**"));
        return Lists.newArrayList(securityContext);
    }

    /**
     * OAuth2.0 的认证方式
     *
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        // 使用密码模式（password）
        List<GrantType> grantTypes = new ArrayList<>();
        String passwordTokenUrl = "http://127.0.0.1:8002/api/auth/oauth/token";
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(passwordTokenUrl);
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        OAuth oAuth = new OAuthBuilder().name("oauth2").grantTypes(grantTypes).build();
        return Lists.newArrayList(oAuth);
    }

}