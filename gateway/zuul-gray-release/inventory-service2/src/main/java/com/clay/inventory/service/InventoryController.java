package com.clay.inventory.service;

import com.clay.inventory.api.InventoryApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi {

    @Override
    @RequestMapping(value = "/deduct/{productId}/{stock}", method = RequestMethod.POST)
    public String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock) {
        System.out.println("库存服务二，对商品【productId=" + productId + "】扣减库存：" + stock);
        return "{'msg': 'success'}";
    }

}