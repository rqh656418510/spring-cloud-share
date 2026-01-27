package org.dromara.hmily.demo.springcloud.order.service;

import org.dromara.hmily.demo.common.order.entity.Order;

import java.math.BigDecimal;

/**
 * OrderService.
 */
public interface OrderService {

    /**
     * 创建订单并且进行扣除账户余额支付，并进行库存扣减操作.
     *
     * @param count  购买数量
     * @param amount 支付金额
     * @return string string
     */
    String orderPay(Integer count, BigDecimal amount);

    /**
     * 模拟在订单支付操作中，库存在try阶段中的库存异常.
     *
     * @param count  购买数量
     * @param amount 支付金额
     * @return string string
     */
    String mockInventoryWithTryException(Integer count, BigDecimal amount);

    /**
     * Mock account with try exception string.
     *
     * @param count  the count
     * @param amount the amount
     * @return the string
     */
    String mockAccountWithTryException(Integer count, BigDecimal amount);

    /**
     * 模拟在订单支付操作中，库存在try阶段中的timeout.
     *
     * @param count  购买数量
     * @param amount 支付金额
     * @return string string
     */
    String mockInventoryWithTryTimeout(Integer count, BigDecimal amount);

    /**
     * Mock account with try timeout string.
     *
     * @param count  the count
     * @param amount the amount
     * @return the string
     */
    String mockAccountWithTryTimeout(Integer count, BigDecimal amount);

    /**
     * Order pay with nested string.
     *
     * @param count  the count
     * @param amount the amount
     * @return the string
     */
    String orderPayWithNested(Integer count, BigDecimal amount);

    /**
     * Order pay with nested exception string.
     *
     * @param count  the count
     * @param amount the amount
     * @return the string
     */
    String orderPayWithNestedException(Integer count, BigDecimal amount);

    /**
     * 更新订单状态.
     *
     * @param order 订单实体类
     */
    void updateOrderStatus(Order order);
}
