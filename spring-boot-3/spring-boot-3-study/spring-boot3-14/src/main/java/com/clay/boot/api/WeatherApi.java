package com.clay.boot.api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * @author clay
 */
@Component
public interface WeatherApi {

    @GetExchange(value = "https://ali-weather.showapi.com/area-to-weather-date", accept = "application/json")
    Mono<String> weather(@RequestParam("area") String city);

}
