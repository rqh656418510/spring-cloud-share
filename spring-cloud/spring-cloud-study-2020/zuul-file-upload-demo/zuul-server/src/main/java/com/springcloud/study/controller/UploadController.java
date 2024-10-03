package com.springcloud.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@Api("Zuul文件上传")
public class UploadController {

    public static final String PREFIX_PATH = "/tmp/upload/";

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/upload")
    @ApiOperation("文件上传接口")
    public String upload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        logger.info("==> file size: " + file.getSize());
        byte[] bytes = file.getBytes();
        String filePath = PREFIX_PATH + UUID.randomUUID().toString();
        File fileToSave = new File(filePath);
        FileCopyUtils.copy(bytes, fileToSave);
        return filePath;
    }

}
