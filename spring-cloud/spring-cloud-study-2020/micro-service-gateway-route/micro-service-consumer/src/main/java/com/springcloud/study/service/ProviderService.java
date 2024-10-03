package com.springcloud.study.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("provider-service")
public interface ProviderService {

    @RequestMapping(value = "/provider/sayHello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name);

}
