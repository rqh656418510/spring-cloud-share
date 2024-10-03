package com.seata.study.client;

import com.seata.study.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@FeignClient("seata-account-service")
public interface AccountClient {

    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
