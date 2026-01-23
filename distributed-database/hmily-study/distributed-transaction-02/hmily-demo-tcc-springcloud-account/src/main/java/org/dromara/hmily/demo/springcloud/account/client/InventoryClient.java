package org.dromara.hmily.demo.springcloud.account.client;

import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.demo.common.inventory.dto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Inventory client.
 */
@FeignClient(value = "inventory-service")
public interface InventoryClient {
    
    /**
     * 库存扣减.
     *
     * @param inventoryDTO 实体对象
     * @return true 成功
     */
    @RequestMapping("/inventory-service/inventory/decrease")
    @Hmily
    Boolean decrease(@RequestBody InventoryDTO inventoryDTO);
    
    /**
     * 模拟库存扣减异常.
     *
     * @param inventoryDTO 实体对象
     * @return true 成功
     */
    @Hmily
    @RequestMapping("/inventory-service/inventory/mockWithTryException")
    Boolean mockWithTryException(@RequestBody InventoryDTO inventoryDTO);
}
