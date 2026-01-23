package org.dromara.hmily.demo.springcloud.order.client;

import org.dromara.hmily.demo.common.inventory.dto.InventoryDTO;
import org.springframework.stereotype.Component;

@Component
public class InventoryHystrix implements InventoryClient {


    @Override
    public Boolean decrease(InventoryDTO inventoryDTO) {
        System.out.println("inventory hystrix.......");
        return false;
    }
    
    @Override
    public Boolean testDecrease(InventoryDTO inventoryDTO) {
        System.out.println("inventory hystrix.......");
        return false;
    }
    
    @Override
    public Integer findByProductId(String productId) {
        return 0;
    }

    @Override
    public Boolean mockWithTryException(InventoryDTO inventoryDTO) {
        return false;
    }

    @Override
    public Boolean mockWithTryTimeout(InventoryDTO inventoryDTO) {
        return false;
    }
}
