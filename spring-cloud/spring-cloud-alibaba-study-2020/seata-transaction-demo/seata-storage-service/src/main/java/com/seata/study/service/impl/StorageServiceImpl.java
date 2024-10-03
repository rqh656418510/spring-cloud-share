package com.seata.study.service.impl;

import com.seata.study.enums.SystemCode;
import com.seata.study.dao.StorageMapper;
import com.seata.study.domain.Storage;
import com.seata.study.service.StorageService;
import com.seata.study.vo.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author clay
 * @version 1.0
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public CommonResult decrease(Long productId, Long count) {
        Storage storage = storageMapper.findByProduct(productId);
        Long total = storage.getTotal();
        Long used = storage.getUsed();
        Long residue = storage.getResidue();
        // 校验参数
        if (count == null || count <= 0) {
            return new CommonResult(SystemCode.ERROR_PARAMETER);
        }
        // 判断库存是否足够
        if (count > residue) {
            return new CommonResult(SystemCode.STORAGE_NOT_ENOUGH);
        }
        // 扣减库存
        storage.setUsed(used + count);
        storage.setResidue(residue - count);
        storageMapper.update(storage);
        return new CommonResult();
    }
}
