package com.distributed.transaction.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * Feign 客户端（加钱服务）
 */
@FeignClient(value = "fencai-hmily-money-to")
public interface AccountClient {

    /**
     * 转账操作 <p>
     * 必须使用 @Hmily 注解修饰方法 <p>
     * 远程调用加钱服务的 try 方法（TCC 的三大方法之一）
     *
     * @param id         账户ID
     * @param transMoney 转账金额（正数：增加余额；负数：减少余额）
     * @return true 成功，false 失败
     */
    @Hmily
    @GetMapping("/account/{id}/{transMoney}")
    boolean transfer(@PathVariable("id") Long id, @PathVariable("transMoney") BigDecimal transMoney);

}