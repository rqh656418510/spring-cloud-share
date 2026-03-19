package com.clay.inventory.controller;

import com.clay.inventory.api.InventoryApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController implements InventoryApi {

    @Override
    public String increaseStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock) {
        System.out.println("库存服务一，对商品【productId=" + productId + "】增加库存：" + stock);
        return "{'msg': 'success'}";
    }

    @Override
    public String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock) {
        System.out.println("库存服务一，对商品【productId=" + productId + "】扣减库存：" + stock);
        return "{'msg': 'success'}";
    }

}