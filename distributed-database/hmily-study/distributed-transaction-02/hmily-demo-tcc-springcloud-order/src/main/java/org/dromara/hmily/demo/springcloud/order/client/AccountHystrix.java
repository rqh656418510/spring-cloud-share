package org.dromara.hmily.demo.springcloud.order.client;

import org.dromara.hmily.demo.common.account.dto.AccountDTO;
import org.dromara.hmily.demo.common.account.dto.AccountNestedDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * The type Account hystrix.
 */
@Component
public class AccountHystrix implements AccountClient {

    @Override
    public Boolean payment(AccountDTO accountDO) {
        System.out.println("执行断路器。。" + accountDO.toString());
        return false;
    }
    
    @Override
    public Boolean testPayment(AccountDTO accountDO) {
        System.out.println("执行断路器。。" + accountDO.toString());
        return false;
    }
    
    @Override
    public BigDecimal findByUserId(String userId) {
        System.out.println("执行断路器。。");
        return BigDecimal.ZERO;
    }
    
    @Override
    public Boolean mockWithTryException(AccountDTO accountDO) {
        return false;
    }
    
    @Override
    public Boolean mockWithTryTimeout(AccountDTO accountDO) {
        return false;
    }
    
    @Override
    public Boolean paymentWithNested(AccountNestedDTO nestedDTO) {
        return false;
    }
    
    @Override
    public Boolean paymentWithNestedException(AccountNestedDTO nestedDTO) {
        return false;
    }
}
