package com.clay.service;

import com.clay.resp.ResultData;

/**
 * @author turing
 * @version 1.0
 */
public interface StorageService {

    /**
     * 扣减库存
     */
    ResultData decrease(Long productId, Integer count);

}
