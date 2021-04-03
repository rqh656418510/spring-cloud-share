package com.clay.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.clay.common.constants.AuthConstants;
import com.clay.common.utils.EncryptUtil;
import com.clay.gateway.swagger.SwaggerResourceConfig;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResource;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 用于认证授权的全局过滤器
 *
 * @author clay
 * @version 1.0
 */
@Component
public class GatewayAuthFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(GatewayAuthFilter.class);

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private SwaggerResourceConfig swaggerResourceConfig;

    /**
     * 是否启用认证授权
     */
    @Value("${spring.cloud.gateway.auth.enabled:true}")
    private boolean authEnabled = true;

    /**
     * 不需要校验的API接口<br>
     * 区分英文大小写，多个接口之间可以使用英文逗号隔开<br>
     */
    @Value("${spring.cloud.gateway.auth.exclude:}")
    private String authExclude;

    /**
     * 默认不需要校验的API接口
     */
    private static final String[] authDefaultExclude = {
            // 认证授权服务的接口
            "/auth-server/oauth/**",
            "/AUTH-SERVER/oauth/**",
            "/auth-server/login",
            "/AUTH-SERVER/login",
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        // 放行不需要校验的API接口
        if (!authEnabled || excludePathMatcher(requestUrl)) {
            return chain.filter(exchange);
        }
        // 放行Swagger2的API文档接口
        if (swaggerPathMatcher(requestUrl)) {
            return chain.filter(exchange);
        }
        // 检查AccessToken是否存在
        String accessToken = getAccessToken(exchange);
        if (StringUtils.isBlank(accessToken)) {
            return noTokenMono(exchange);
        }
        // 判断AccessToken是否有效
        OAuth2AccessToken oAuth2AccessToken;
        try {
            // 解析AccessToken
            oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
            // 获取用户身份信息
            String principal = MapUtils.getString(additionalInformation, "user_name");
            // 获取用户权限
            List<String> authorities = (List<String>) additionalInformation.get("authorities");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(AuthConstants.USER_PRINCIPAL_KEY, principal);
            jsonObject.put(AuthConstants.USER_AUTHORITIES_KEY, authorities);
            // 往Header里面添加UserToken
            String userToken = EncryptUtil.encodeUTF8StringBase64(jsonObject.toJSONString());
            ServerHttpRequest tokenRequest = exchange.getRequest().mutate().header(AuthConstants.USER_TOKEN_KEY, userToken).build();
            ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
            return chain.filter(build);
        } catch (Exception e) {
            logger.error("解析 AccessToken 失败 : {} \n {}", accessToken, e.getLocalizedMessage());
            return invalidTokenMono(exchange);
        }
    }

    /**
     * 获取 AccessToken
     */
    private String getAccessToken(ServerWebExchange exchange) {
        String accessTokenStr = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(accessTokenStr)) {
            return null;
        }
        String[] strArray = accessTokenStr.split(" ");
        if (strArray.length > 1) {
            String accessToken = accessTokenStr.split(" ")[1];
            if (StringUtils.isNotBlank(accessToken)) {
                return accessToken;
            }
        }
        return null;
    }

    /**
     * 判断是否为不需要校验的API接口<br>
     * true 是 false 否
     *
     * @param requestUrl
     * @return
     */
    public boolean excludePathMatcher(String requestUrl) {
        Set<String> apiExcludeSet = new HashSet<>();
        Collections.addAll(apiExcludeSet, authDefaultExclude);

        if (StringUtils.isNoneBlank(authExclude)) {
            String[] otherApi = authExclude.split(",");
            Collections.addAll(apiExcludeSet, otherApi);
        }
        // 验证URL
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String exclude : apiExcludeSet) {
            if (pathMatcher.match(exclude.trim(), requestUrl)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为Swagger2的API文档接口<br>
     * true 是 false 否
     *
     * @param requestUrl
     * @return
     */
    public boolean swaggerPathMatcher(String requestUrl) {
        // Swagger2的API文档接口的格式为 /serviceId/v2/api-doc
        AntPathMatcher pathMatcher = new AntPathMatcher();
        List<SwaggerResource> resources = swaggerResourceConfig.get();
        for (SwaggerResource resource : resources) {
            String url = resource.getUrl().trim();
            if (pathMatcher.match(url, requestUrl)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> invalidTokenMono(ServerWebExchange exchange) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.UNAUTHORIZED.value());
        json.put("data", "无效的 AccessToken");
        return buildReturnMono(json, exchange);
    }

    private Mono<Void> noTokenMono(ServerWebExchange exchange) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.UNAUTHORIZED.value());
        json.put("data", "缺少 AccessToken");
        return buildReturnMono(json, exchange);
    }

    private Mono<Void> buildReturnMono(JSONObject json, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bits = json.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 指定编码，否则在浏览器中会出现中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }

}