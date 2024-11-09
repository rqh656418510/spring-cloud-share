package com.clay.apis;

import com.clay.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author clay
 * @version 1.0
 */
@FeignClient(value = "provider-service")
public interface ProviderFeignApi {

    @GetMapping("/provider/pay/get/{orderNumber}")
    ResultData getPayByOrderNumber(@PathVariable("orderNumber") String orderNumber);

}
