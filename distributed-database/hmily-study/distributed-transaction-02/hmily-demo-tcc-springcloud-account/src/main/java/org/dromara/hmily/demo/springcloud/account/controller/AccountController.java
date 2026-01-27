package org.dromara.hmily.demo.springcloud.account.controller;

import org.dromara.hmily.demo.common.account.dto.AccountDTO;
import org.dromara.hmily.demo.common.account.dto.AccountNestedDTO;
import org.dromara.hmily.demo.springcloud.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * AccountController.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/payment")
    public Boolean payment(@RequestBody AccountDTO accountDO) {
        return accountService.payment(accountDO);
    }

    @RequestMapping("/mockWithTryException")
    public Boolean mockWithTryException(@RequestBody AccountDTO accountDO) {
        return accountService.mockWithTryException(accountDO);
    }

    @RequestMapping("/mockWithTryTimeout")
    public Boolean mockWithTryTimeout(@RequestBody AccountDTO accountDO) {
        return accountService.mockWithTryTimeout(accountDO);
    }

    @RequestMapping("/paymentWithNested")
    public Boolean paymentWithNested(@RequestBody AccountNestedDTO nestedDTO) {
        return accountService.paymentWithNested(nestedDTO);
    }

    @RequestMapping("/paymentWithNestedException")
    public Boolean paymentWithNestedException(@RequestBody AccountNestedDTO nestedDTO) {
        return accountService.paymentWithNestedException(nestedDTO);
    }

    @RequestMapping("/findByUserId")
    public BigDecimal findByUserId(@RequestParam("userId") String userId) {
        return accountService.findByUserId(userId).getBalance();
    }
}
