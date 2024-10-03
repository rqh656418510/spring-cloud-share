package com.springcloud.study.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FeignUploadController {

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(@RequestPart(value = "file") MultipartFile file) throws Exception {
        return "file-name: " + file.getOriginalFilename() + " file-size: " + file.getSize();
    }

}
