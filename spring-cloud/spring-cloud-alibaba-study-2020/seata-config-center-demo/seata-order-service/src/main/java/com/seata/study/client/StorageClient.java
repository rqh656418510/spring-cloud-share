package com.seata.study.client;

import com.seata.study.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author clay
 * @version 1.0
 */
@FeignClient("seata-storage-service")
public interface StorageClient {

    /**
     * 扣减商品库存
     *
     * @param productId
     * @param count
     * @return
     */
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Long count);
}
