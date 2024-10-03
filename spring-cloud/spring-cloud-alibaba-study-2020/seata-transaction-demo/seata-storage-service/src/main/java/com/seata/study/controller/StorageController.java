package com.seata.study.controller;

import com.seata.study.service.StorageService;
import com.seata.study.vo.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author clay
 * @version 1.0
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/decrease")
    public CommonResult decrease(Long productId, Long count) {
        return storageService.decrease(productId, count);
    }
}
