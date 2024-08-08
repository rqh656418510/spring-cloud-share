package com.clay.boot.api;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

/**
 * @author clay
 */
@HttpExchange
public interface WeatherApi {

    @GetExchange(value = "/area-to-weather-date", accept = "application/json")
    Mono<String> weather(@RequestParam("area") String city);

}
