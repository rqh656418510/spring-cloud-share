package com.seata.study.service;

import com.seata.study.vo.CommonResult;

/**
 * @author clay
 * @version 1.0
 */
public interface StorageService {

    /**
     * 扣减库存
     *
     * @param productId 商品ID
     * @param count     数量
     * @return
     */
    public CommonResult decrease(Long productId, Long count);

}
