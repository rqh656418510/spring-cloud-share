package com.java.juc;

import com.alibaba.fastjson2.JSON;
import com.java.juc.entity.CustomerMixInfo;
import com.java.juc.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ThreadPoolTest {

    @Resource
    private CustomerService customerService;

    @Test
    public void invoke() {
        CustomerMixInfo customerMixInfo = customerService.findCustomer();
        log.info(JSON.toJSONString(customerMixInfo));
    }

}
