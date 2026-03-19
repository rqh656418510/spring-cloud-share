package com.clay.wms.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/wms")
public interface WmsApi {

    /**
     * 增加库存
     */
    @RequestMapping(value = "/stock/increase/{productId}/{stock}", method = RequestMethod.POST)
    String increaseStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

    /**
     * 扣减库存
     */
    @RequestMapping(value = "/stock/deduct/{productId}/{stock}", method = RequestMethod.POST)
    String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

}