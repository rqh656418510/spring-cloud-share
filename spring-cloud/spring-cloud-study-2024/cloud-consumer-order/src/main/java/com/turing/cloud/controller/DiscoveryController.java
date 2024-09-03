package com.turing.cloud.controller;

import com.turing.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author turing
 * @version 1.0
 */
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/service/list")
    @Operation(summary = "获取服务列表", description = "查询服务列表")
    public ResultData<List<String>> serviceList() {
        List<String> resultList = new ArrayList<>();

        // 获取服务名称
        List<String> services = discoveryClient.getServices();
        services.stream().forEach(service -> {
            // 获取服务实例
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            instances.stream().forEach(instance -> {
                StringBuilder builder = new StringBuilder();
                builder.append(instance.getInstanceId()).append("\t").append(instance.getHost()).append("\t")
                    .append(instance.getPort()).append("\t").append(instance.getUri());
                resultList.add(builder.toString());
            });
        });

        return ResultData.success(resultList);
    }

}
