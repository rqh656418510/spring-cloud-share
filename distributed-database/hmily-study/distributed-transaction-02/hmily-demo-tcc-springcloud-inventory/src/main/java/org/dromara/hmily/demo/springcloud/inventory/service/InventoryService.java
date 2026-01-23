package org.dromara.hmily.demo.springcloud.inventory.service;

import org.dromara.hmily.demo.common.inventory.dto.InventoryDTO;
import org.dromara.hmily.demo.common.inventory.entity.InventoryDO;

/**
 * The interface Inventory service.
 */
public interface InventoryService {
    
    /**
     * 扣减库存操作.
     * 这一个tcc接口
     *
     * @param inventoryDTO 库存DTO对象
     * @return true boolean
     */
    Boolean decrease(InventoryDTO inventoryDTO);
    
    /**
     * Test decrease boolean.
     *
     * @param inventoryDTO the inventory dto
     * @return the boolean
     */
    Boolean testDecrease(InventoryDTO inventoryDTO);
    
    /**
     * 获取商品库存信息.
     *
     * @param productId 商品id
     * @return InventoryDO inventory do
     */
    InventoryDO findByProductId(String productId);
    
    /**
     * mock 库存扣减try阶段异常.
     *
     * @param inventoryDTO dto
     * @return true boolean
     */
    Boolean mockWithTryException(InventoryDTO inventoryDTO);
    
    /**
     * mock 库存扣减try阶段超时.
     *
     * @param inventoryDTO dto
     * @return true boolean
     */
    Boolean mockWithTryTimeout(InventoryDTO inventoryDTO);
}
