package com.clay.boot.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author clay
 */
@Service
public class WeatherService {

    public Mono<String>  weather(String city) {
        // 请求参数
        Map<String, String> params = new HashMap<>();
        params.put("area", city);

        // 发送请求（异步）
        WebClient client = WebClient.create();
        return client.get()
            .uri("https://ali-weather.showapi.com/area-to-weather-date?area={area}", params)
            .header("Authorization", "APPCODE 93b7e19861a24c519a7548b17dc16d75")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class);
    }

}
