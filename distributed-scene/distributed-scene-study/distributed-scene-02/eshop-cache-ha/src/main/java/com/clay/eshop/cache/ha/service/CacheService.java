package com.clay.eshop.cache.ha.service;

import com.alibaba.fastjson.JSON;
import com.clay.eshop.cache.ha.command.GetCityNameCommand;
import com.clay.eshop.cache.ha.command.GetProductInfoCommand;
import com.clay.eshop.cache.ha.command.GetProductInfosCommand;
import com.clay.eshop.cache.ha.model.ProductInfo;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Observer;

@Service
public class CacheService {

    /**
     * 更新单个商品的缓存数据
     */
    public boolean changeProduct(Long productId) {
        GetProductInfoCommand infoCommand = new GetProductInfoCommand(productId);
        ProductInfo productInfo = infoCommand.execute();

        // 从本地缓存获取城市名称
        GetCityNameCommand cityCommand = new GetCityNameCommand(productInfo.getCityId());
        String cityName = cityCommand.execute();
        productInfo.setCityName(cityName);

        System.out.println(JSON.toJSONString(productInfo));
        return true;
    }

    /**
     * 更新多个商品的缓存数据
     */
    public boolean changeProducts(String productIds) {
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

        return true;
    }

    /**
     * 更新多个商品的缓存数据（基于Hystrix的请求缓存）
     */
    public boolean changeProducts2(String productIds) {
        String[] ids = productIds.split(",");
        for (String id : ids) {
            GetProductInfoCommand command = new GetProductInfoCommand(Long.valueOf(id));
            ProductInfo productInfo = command.execute();
            System.out.println(JSON.toJSONString(productInfo));
        }

        return true;
    }

}