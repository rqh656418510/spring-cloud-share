package org.dromara.hmily.demo.springcloud.order.service;

import org.dromara.hmily.demo.common.order.entity.Order;

/**
 * PaymentService.
 */
public interface PaymentService {
    
    /**
     * 订单支付.
     *
     * @param order 订单实体
     */
    void makePayment(Order order);
    
    /**
     * Test make payment.
     *
     * @param order the order
     */
    void testMakePayment(Order order);
    
    /**
     * mock订单支付的时候库存异常.
     *
     * @param order 订单实体
     * @return String string
     */
    String mockPaymentInventoryWithTryException(Order order);
    
    /**
     * Mock payment account with try exception string.
     *
     * @param order the order
     * @return the string
     */
    String mockPaymentAccountWithTryException(Order order);
    
    /**
     * mock订单支付的时候库存超时.
     *
     * @param order 订单实体
     * @return String string
     */
    String mockPaymentInventoryWithTryTimeout(Order order);
    
    /**
     * Mock payment account with try timeout string.
     *
     * @param order the order
     * @return the string
     */
    String mockPaymentAccountWithTryTimeout(Order order);
    
    /**
     * Make payment with nested.
     *
     * @param order the order
     * @return the string
     */
    String makePaymentWithNested(Order order);
    
    /**
     * Make payment with nested exception.
     *
     * @param order the order
     * @return the string
     */
    String makePaymentWithNestedException(Order order);
}
