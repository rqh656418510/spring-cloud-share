package com.clay.wms.controller;

import com.clay.wms.api.WmsApi;
import com.clay.wms.client.InventoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WmsController implements WmsApi {

    @Autowired
    private InventoryFeignClient inventoryService;

    @Override
    public String increaseStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock) {
        System.out.println("仓管服务二，对商品【productId=" + productId + "】增加库存：" + stock);
        inventoryService.increaseStock(productId, stock);
        return "{'msg': 'success'}";
    }

    @Override
    public String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock) {
        System.out.println("仓管服务二，对商品【productId=" + productId + "】扣减库存：" + stock);
        inventoryService.deductStock(productId, stock);
        return "{'msg': 'success'}";
    }

}