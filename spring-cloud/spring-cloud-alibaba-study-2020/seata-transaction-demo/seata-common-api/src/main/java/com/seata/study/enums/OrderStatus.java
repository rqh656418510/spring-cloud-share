package com.seata.study.enums;

/**
 * 订单状态
 *
 * @author clay
 * @version 1.0
 */
public enum OrderStatus {

    /**
     * 创建中
     */
    CREATING(0),

    /**
     * 已经完成
     */
    FINISHED(1);

    private int value;

    private OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
