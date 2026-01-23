package org.dromara.hmily.demo.common.inventory.api;

import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.demo.common.inventory.dto.InventoryDTO;
import org.dromara.hmily.demo.common.inventory.entity.InventoryDO;

import java.util.List;

/**
 * The interface Inventory service.
 */
public interface InventoryService {

    /**
     * 扣减库存操作
     * 这一个tcc接口
     *
     * @param inventoryDTO 库存DTO对象
     * @return true boolean
     */
    @Hmily
    Boolean decrease(InventoryDTO inventoryDTO);

    /**
     * Test in line list.
     *
     * @return the list
     */
    @Hmily
    List<InventoryDTO> testInLine();

    /**
     * Test decrease boolean.
     *
     * @param inventoryDTO the inventory dto
     * @return the boolean
     */
    Boolean testDecrease(InventoryDTO inventoryDTO);

    /**
     * 获取商品库存信息
     *
     * @param productId 商品id
     * @return InventoryDO inventory do
     */
    InventoryDO findByProductId(String productId);

    /**
     * mock扣减库存异常
     *
     * @param inventoryDTO dto对象
     * @return String string
     */
    @Hmily
    String mockWithTryException(InventoryDTO inventoryDTO);

    /**
     * mock扣减库存超时
     *
     * @param inventoryDTO dto对象
     * @return String boolean
     */
    @Hmily
    Boolean mockWithTryTimeout(InventoryDTO inventoryDTO);

    /**
     * mock 扣减库存confirm超时
     *
     * @param inventoryDTO dto对象
     * @return True boolean
     */
    @Hmily
    Boolean mockWithConfirmTimeout(InventoryDTO inventoryDTO);
}
