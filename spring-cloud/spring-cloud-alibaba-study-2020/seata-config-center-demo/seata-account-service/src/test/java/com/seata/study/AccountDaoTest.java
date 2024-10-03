package com.seata.study;

import com.seata.study.dao.AccountMapper;
import com.seata.study.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author clay
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void findByUser() {
        Account account = accountMapper.findByUser(1L);
        Assert.notNull(account, "object is null");
    }

    @Test
    public void update() {
        Account account = accountMapper.findByUser(1L);
        account.setUsed(account.getUsed().add(BigDecimal.TEN));
        account.setResidue(account.getResidue().subtract(BigDecimal.TEN));
        accountMapper.update(account);
    }
}
