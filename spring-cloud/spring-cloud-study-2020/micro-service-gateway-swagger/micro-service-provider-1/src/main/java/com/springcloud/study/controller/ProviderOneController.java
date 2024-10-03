package com.springcloud.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("provider-service-1 接口测试")
@RequestMapping("/provider1")
public class ProviderOneController {

    @ApiOperation(value = "计算+", notes = "加法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a", value = "数字a", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "b", value = "数字b", required = true, dataType = "Long")
    })
    @GetMapping("/{a}/{b}")
    public String get(@PathVariable Integer a, @PathVariable Integer b) {
        return "from provider service 1, the result is: " + (a + b);
    }
}
