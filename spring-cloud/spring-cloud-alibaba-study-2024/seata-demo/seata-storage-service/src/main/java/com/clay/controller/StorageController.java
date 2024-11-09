package com.clay.controller;

import com.clay.resp.ResultData;
import com.clay.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author turing
 * @version 1.0
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @PostMapping("/storage/decrease")
    public ResultData decrease(Long productId, Integer count) {
        return storageService.decrease(productId, count);
    }

}
