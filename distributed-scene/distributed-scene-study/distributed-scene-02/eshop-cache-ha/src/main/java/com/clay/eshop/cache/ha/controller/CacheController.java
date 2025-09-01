package com.clay.eshop.cache.ha.controller;

import com.clay.eshop.cache.ha.model.ProductInfo;
import com.clay.eshop.cache.ha.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 缓存服务的接口
 */
@Controller
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private CacheService cacheService;

    /**
     * 更新单个商品的缓存数据
     */
    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Long productId) {
        cacheService.changeProduct(productId);
        return "Product cache update success";
    }

    /**
     * 更新多个商品的缓存数据
     */
    @RequestMapping("/change/products")
    @ResponseBody
    public String changeProducts(String productIds) {
        cacheService.changeProducts(productIds);
        return "Products cache update success";
    }

    /**
     * 更新多个商品的缓存数据（基于Hystrix的请求缓存）
     */
    @RequestMapping("/change/products2")
    @ResponseBody
    public String changeProducts2(String productIds) {
        cacheService.changeProducts2(productIds);
        return "Products cache update success";
    }

    /**
     * 更新单个商品的缓存数据（用于验证熔断机制）
     */
    @RequestMapping("/get/product")
    @ResponseBody
    public ProductInfo getProductInfo(Long productId) {
        return cacheService.getProductInfo(productId);
    }

}