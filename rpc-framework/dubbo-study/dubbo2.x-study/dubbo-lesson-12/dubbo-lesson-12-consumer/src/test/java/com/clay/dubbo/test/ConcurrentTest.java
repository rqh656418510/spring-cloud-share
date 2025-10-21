package com.clay.dubbo.test;

import com.clay.dubbo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentTest {

    public static void main(String[] args) throws Exception {
        multiThreadTest();
    }

    /**
     * 多个线程测试
     */
    public static void multiThreadTest() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            pool.submit(() -> {
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity entity = restTemplate.getForEntity("http://127.0.0.1:8000/user/getById/" + index, User.class);
                    System.out.println("request-" + index + " " + entity.getStatusCode());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        pool.shutdown();
    }

}
