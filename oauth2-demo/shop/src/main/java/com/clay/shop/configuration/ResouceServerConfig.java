package com.clay.shop.configuration;

import cn.hutool.core.util.StrUtil;
import com.clay.shop.security.handler.CustomAccessDeniedHandler;
import com.clay.common.constants.AuthConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源配置
 *
 * @author clay
 * @version 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    /**
     * 客户端的授权范围
     */
    @Value("${auth.client.scope:}")
    private String authClientScope;

    /**
     * 客户端接入的资源列表
     */
    @Value("${auth.client.resource:}")
    private String authClientResource;

    /**
     * 不需要校验的API接口<br>
     * 区分英文大小写，多个接口之间可以使用英文逗号隔开<br>
     */
    @Value("${auth.exclude:}")
    private String authExclude;

    /**
     * 默认不需要校验的API接口
     */
    private static final String[] authDefaultExclude = {
            // Swagger2的接口
            AuthConstants.API_SWAGGER2,
            // Web应用的首页
            AuthConstants.WEB_APPLICATION_INDEX,
            // Spring Boot Actuator的接口
            AuthConstants.API_SPRING_BOOT_ACTUATOR
    };

    /**
     * 资源配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(authClientResource)
                .tokenStore(tokenStore)
                .stateless(true)
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    /**
     * 校验HTTP请求
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(getAuthExcludeApi()).permitAll()
                .antMatchers("/**").access(getAuthClientScope())
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 获取客户端的授权范围
     *
     * @return
     */
    private String getAuthClientScope() {
        String format = "#oauth2.hasScope('%s')";
        if (StrUtil.isNotBlank(authClientScope)) {
            return String.format(format, authClientScope);
        }
        return String.format(format, "");
    }

    /**
     * 获取不需要校验的API接口
     *
     * @return
     */
    private String[] getAuthExcludeApi() {
        Set<String> apiExcludeSet = new HashSet<String>();
        Collections.addAll(apiExcludeSet, authDefaultExclude);
        if (StringUtils.isNoneBlank(authExclude)) {
            String[] otherArray = authExclude.split(",");
            for (String other : otherArray) {
                apiExcludeSet.add(other.trim());
            }
        }
        return apiExcludeSet.toArray(new String[0]);
    }

}
