package com.clay.eshop.cache.ha.command;

import com.alibaba.fastjson.JSONObject;
import com.clay.eshop.cache.ha.http.HttpClientUtils;
import com.clay.eshop.cache.ha.model.ProductInfo;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 获取多个商品的信息
 */
public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {

    private final String[] productIds;

    public GetProductInfosCommand(String[] productId) {
        super(HystrixObservableCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductServiceGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfosCommand")));
        this.productIds = productId;
    }

    @Override
    protected Observable<ProductInfo> construct() {
        return Observable.create(new Observable.OnSubscribe<ProductInfo>() {
            @Override
            public void call(Subscriber<? super ProductInfo> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (String productId : productIds) {
                            // 调用商品服务的接口，获取商品ID对应的商品的最新数据，用HttpClient去调用商品服务的Http接口
                            String url = "http://127.0.0.1:9092/product/info?productId=" + productId;
                            String response = HttpClientUtils.sendGetRequest(url);
                            ProductInfo productInfo = JSONObject.parseObject(response, ProductInfo.class);
                            subscriber.onNext(productInfo);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

}
