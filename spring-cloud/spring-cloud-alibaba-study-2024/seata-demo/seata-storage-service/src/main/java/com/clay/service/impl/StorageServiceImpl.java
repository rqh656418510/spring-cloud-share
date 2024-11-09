package com.clay.service.impl;

import com.clay.entities.Storage;
import com.clay.mapper.StorageMapper;
import com.clay.resp.ResultData;
import com.clay.resp.ReturnCodeEnum;
import com.clay.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author turing
 * @version 1.0
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public ResultData decrease(Long productId, Integer count) {
        Storage storage = storageMapper.selectByProductId(productId);
        Integer residue = storage.getResidue();
        // 校验参数
        if (count == null || count <= 0) {
            return ResultData.fail(ReturnCodeEnum.RC380);
        }
        // 判断库存是否足够
        if (count > residue) {
            return ResultData.fail(ReturnCodeEnum.STORAGE_NOT_ENOUGH);
        }

        log.info("------->库存服务中扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("------->库存服务中扣减库存结束");

        return ResultData.success();
    }

}
