package org.dromara.hmily.demo.common.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Order status enum.
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    /**
     * Not pay order status enum.
     */
    NOT_PAY(1, "未支付"),

    /**
     * Paying order status enum.
     */
    PAYING(2, "支付中"),

    /**
     * Pay fail order status enum.
     */
    PAY_FAIL(3, "支付失败"),

    /**
     * Pay success order status enum.
     */
    PAY_SUCCESS(4, "支付成功");

    private final int code;

    private final String desc;
}