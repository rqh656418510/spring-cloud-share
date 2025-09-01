package com.clay.eshop.cache.ha.local;

import java.util.HashMap;
import java.util.Map;

/**
 * 品牌缓存
 */
public class BrandCache {

    /**
     * 本地缓存（模拟Ehcache）
     */
    private static Map<Long, String> brandMap = new HashMap<Long, String>();

    static {
        brandMap.put(1L, "苹果（Apple）");
    }

    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }

}