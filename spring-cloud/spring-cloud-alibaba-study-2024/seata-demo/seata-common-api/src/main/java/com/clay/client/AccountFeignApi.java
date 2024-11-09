package com.clay.client;

import com.clay.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {

    /**
     * 扣减账户余额
     */
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
