package com.springcloud.study.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("consul-provider")
public interface ProviderService {

    @RequestMapping(value = "/provider/sayHello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name);
}
