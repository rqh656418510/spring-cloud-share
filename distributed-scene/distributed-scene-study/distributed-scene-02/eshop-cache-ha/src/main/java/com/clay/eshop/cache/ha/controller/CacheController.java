package com.clay.eshop.cache.ha.controller;

import com.alibaba.fastjson.JSON;
import com.clay.eshop.cache.ha.command.GetCityNameCommand;
import com.clay.eshop.cache.ha.command.GetProductInfoCommand;
import com.clay.eshop.cache.ha.command.GetProductInfosCommand;
import com.clay.eshop.cache.ha.model.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;
import rx.Observer;

/**
 * 缓存服务的接口
 */
@Controller
@RequestMapping("/cache")
public class CacheController {

    /**
     * 更新单个商品的缓存数据
     */
    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Long productId) {
        GetProductInfoCommand infoCommand = new GetProductInfoCommand(productId);
        ProductInfo productInfo = infoCommand.execute();

        // 从本地缓存获取城市名称
        GetCityNameCommand cityCommand = new GetCityNameCommand(productInfo.getCityId());
        String cityName = cityCommand.execute();
        productInfo.setCityName(cityName);

        System.out.println(JSON.toJSONString(productInfo));
        return "Product cache update success";
    }

    /**
     * 更新多个商品的缓存数据
     */
    @RequestMapping("/change/products")
    @ResponseBody
    public String changeProducts(String productIds) {
        GetProductInfosCommand command = new GetProductInfosCommand(productIds.split(","));
        Observable<ProductInfo> observable = command.observe();
        observable.subscribe(new Observer<ProductInfo>() {
            @Override
            public void onCompleted() {
                System.out.println("获取完所需多个商品的信息");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                System.out.println(JSON.toJSONString(productInfo));
            }
        });

        return "Products cache update success";
    }

}
