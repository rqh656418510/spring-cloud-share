package com.clay.eshop.cache.ha;

import com.clay.eshop.cache.ha.http.HttpClientUtils;

/**
 * 测试Hystrix的断路器
 */
public class CircuitBreakerTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 50; i++) {
            if (i <= 15) {
                // 正常调用
                String productInfo = HttpClientUtils.sendGetRequest("http://127.0.0.1:9091/cache/get/product?productId=1");
                System.out.println("第 " + i + " 个请求，结果为 " + productInfo);
            } else if (i <= 35) {
                // 非法调用（触发断路器打开）
                String productInfo = HttpClientUtils.sendGetRequest("http://127.0.0.1:9091/cache/get/product?productId=-1");
                System.out.println("第 " + i + " 个请求，结果为 " + productInfo);
            }
        }

        System.out.println("==> 尝试等待 10 秒中");
        Thread.sleep(10000);

        for (int i = 1; i <= 10; i++) {
            // 正常调用（断路器从半开状态转换为关闭状态）
            String productInfo = HttpClientUtils.sendGetRequest("http://127.0.0.1:9091/cache/get/product?productId=1");
            System.out.println("第 " + i + " 个请求，结果为 " + productInfo);
        }
    }

}