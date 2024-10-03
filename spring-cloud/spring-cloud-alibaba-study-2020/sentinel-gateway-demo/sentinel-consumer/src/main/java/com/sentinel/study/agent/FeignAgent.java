package com.sentinel.study.agent;

import com.sentinel.study.service.FallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author clay
 */
@FeignClient(value = "sentinel-provider", fallback = FallbackService.class)
public interface FeignAgent {

    @GetMapping("/provider/hello")
    public String hello();
}
