package com.springcloud.com.controller;

import com.springcloud.com.model.GatewayRouteDefinition;
import com.springcloud.com.route.DynamicRouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * 增加路由
     *
     * @param gwdefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = dynamicRouteService.assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.add(definition) ? "success" : "fail";
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id) ? "success" : "fail";
    }

    /**
     * 更新路由
     *
     * @param gwdefinition
     * @return
     */
    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = dynamicRouteService.assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.update(definition) ? "success" : "fail";
    }

}
