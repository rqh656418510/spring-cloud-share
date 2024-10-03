package com.clay.apis;

import com.clay.config.FeignRetryerConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author clay
 * @version 1.0
 */
@FeignClient(value = "provider-service", configuration = FeignRetryerConfig.class)
public interface ProviderFeignApi {

    @GetMapping("/provider/add")
    String add(@RequestParam Integer a, @RequestParam Integer b);

    @GetMapping("/provider/divide")
    String divide(@RequestParam Integer a, @RequestParam Integer b);

}
