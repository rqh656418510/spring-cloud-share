package com.seata.study;

import com.seata.study.service.AccountService;
import com.seata.study.vo.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Resource
    private AccountService accountService;

    @Test
    public void decrease() {
        CommonResult result = accountService.decrease(1L, new BigDecimal(980));
        System.out.println(result);
    }
}
