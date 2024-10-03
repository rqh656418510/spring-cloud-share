package com.nacos.study.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author clay
 */
@FeignClient("provider-service")
public interface ProviderClient {

    @GetMapping("/provider/call")
    public String call();

}
