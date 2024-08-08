package com.clay.boot.service;

import com.clay.boot.api.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author clay
 */
@Service
public class WeatherService {

    @Autowired
    private WeatherApi weatherApi;

    public Mono<String> weather(String city) {
        // 发送请求（异步）
        return weatherApi.weather(city);
    }

}
