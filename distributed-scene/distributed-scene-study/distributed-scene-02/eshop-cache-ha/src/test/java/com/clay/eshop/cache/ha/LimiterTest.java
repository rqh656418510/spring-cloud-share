package com.clay.eshop.cache.ha;

import com.clay.eshop.cache.ha.http.HttpClientUtils;

/**
 * 测试Hystrix的限流机制
 */
public class LimiterTest {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 25; i++) {
            int index = i;
            new Thread(() -> {
                if (index <= 10) {
                    // 耗时调用
                    String productInfo = HttpClientUtils.sendGetRequest("http://127.0.0.1:9091/cache/get/product?productId=-2");
                    System.out.println("第 " + index + " 个请求，结果为 " + productInfo);
                } else {
                    // 快速调用
                    String productInfo = HttpClientUtils.sendGetRequest("http://127.0.0.1:9091/cache/get/product?productId=1");
                    System.out.println("第 " + index + " 个请求，结果为 " + productInfo);
                }
            }).start();
        }
    }

}