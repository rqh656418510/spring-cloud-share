package org.dromara.hmily.demo.common.inventory.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Inventory do.
 */
@Data
public class InventoryDO implements Serializable {

    private static final long serialVersionUID = 6957734749389133832L;

    private Integer id;

    /**
     * 商品id.
     */
    private String productId;

    /**
     * 总库存.
     */
    private Integer totalInventory;

    /**
     * 锁定库存.
     */
    private Integer lockInventory;
}
