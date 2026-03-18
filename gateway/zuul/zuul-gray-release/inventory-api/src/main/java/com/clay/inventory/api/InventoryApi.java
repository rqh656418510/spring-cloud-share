package com.clay.inventory.api;

import org.springframework.web.bind.annotation.PathVariable;

public interface InventoryApi {

    /**
     * 增加库存
     */
    String increaseStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

    /**
     * 扣减库存
     */
    String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

}