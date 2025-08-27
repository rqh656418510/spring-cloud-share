package com.clay.eshop.cache.ha.controller;

import com.clay.eshop.cache.ha.http.HttpClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 缓存服务的接口
 */
@Controller
@RequestMapping("/cache")
public class CacheController {

    /**
     * 更新商品信息的缓存数据
     */
    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Long productId) {
        // 调用商品服务的接口，获取商品ID对应的商品的最新数据，用HttpClient去调用商品服务的Http接口
        String url = "http://127.0.0.1:9092/product/info?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);

        return "success";
    }

}
