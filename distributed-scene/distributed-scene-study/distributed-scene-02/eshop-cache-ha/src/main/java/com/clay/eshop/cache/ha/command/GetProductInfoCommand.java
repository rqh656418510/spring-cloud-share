package com.clay.eshop.cache.ha.command;

import com.alibaba.fastjson.JSONObject;
import com.clay.eshop.cache.ha.http.HttpClientUtils;
import com.clay.eshop.cache.ha.model.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 获取单个商品的信息
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private final Long productId;

    public GetProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        // 调用商品服务的接口，获取商品ID对应的商品的最新数据，用HttpClient去调用商品服务的Http接口
        String url = "http://127.0.0.1:9092/product/info?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

}
