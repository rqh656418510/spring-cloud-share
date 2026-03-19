package com.clay.inventory.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/inventory")
public interface InventoryApi {

    /**
     * 增加库存
     */
    @RequestMapping(value = "/increase/{productId}/{stock}", method = RequestMethod.POST)
    String increaseStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

    /**
     * 扣减库存
     */
    @RequestMapping(value = "/deduct/{productId}/{stock}", method = RequestMethod.POST)
    String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

}