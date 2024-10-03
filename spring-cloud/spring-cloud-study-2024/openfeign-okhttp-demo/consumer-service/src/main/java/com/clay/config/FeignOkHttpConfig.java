package com.clay.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author clay
 * @version 1.0
 */
@Slf4j
@Configuration
public class FeignOkHttpConfig {

    @Bean
    public okhttp3.OkHttpClient.Builder okHttpClientBuilder() {
        return new okhttp3.OkHttpClient.Builder().addInterceptor(new LoggingInterceptor());
    }

    /**
     * Okhttp 请求日志拦截器
     */
    private static class LoggingInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long start = System.nanoTime();
            log.info(
                String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            okhttp3.Response response = chain.proceed(request);
            long end = System.nanoTime();
            log.info(
                String.format("Received response for %s in %.1fms%n%s", response.request().url(), (end - start) / 1e6d,
                    response.headers()));
            return response;
        }

    }

}