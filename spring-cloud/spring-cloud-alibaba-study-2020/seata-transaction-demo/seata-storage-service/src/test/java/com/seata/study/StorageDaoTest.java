package com.seata.study;

import com.seata.study.dao.StorageMapper;
import com.seata.study.domain.Storage;
import org.junit.Assert;
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
public class StorageDaoTest {

    @Resource
    private StorageMapper storageMapper;

    @Test
    public void findByProduct() {
        Storage storage = storageMapper.findByProduct(1L);
        Assert.assertNotNull(storage);
    }

    @Test
    public void update() {
        long count = 5L;
        Storage storage = storageMapper.findByProduct(1L);
        storage.setUsed(storage.getUsed() + count);
        storage.setResidue(storage.getResidue() - count);
        storageMapper.update(storage);
    }
}
