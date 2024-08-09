package com.clay.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付交易
 *
 * @author clay
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDTO implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 订单流水号
     */
    private String orderNo;

    /**
     * 用户账号ID
     */
    private Integer userId;

    /**
     * 交易金额
     */
    private BigDecimal amount;

}
