package com.seata.study;

import com.seata.study.service.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author clay
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceTest {

    @Resource
    private StorageService storageService;

    @Test
    public void decrease() {
        storageService.decrease(1L, 2L);
    }
}
