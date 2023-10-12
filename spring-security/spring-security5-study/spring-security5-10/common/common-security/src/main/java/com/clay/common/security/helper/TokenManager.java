package com.clay.common.security.helper;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Token操作工具
 *
 * @author clay
 */
@Component
public class TokenManager {

    /**
     * 签名秘钥
     */
    private String tokenSignKey = "123456";

    /**
     * Token的有效时长
     */
    private long tokenExpireSeconds = 24 * 60 * 60 * 1000;

    /**
     * 生成Token
     *
     * @param username
     * @return
     */
    public String createToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + tokenExpireSeconds);
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
    }

    /**
     * 根据Token获取用户信息
     *
     * @param token
     * @return
     */
    public String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
    }

}
