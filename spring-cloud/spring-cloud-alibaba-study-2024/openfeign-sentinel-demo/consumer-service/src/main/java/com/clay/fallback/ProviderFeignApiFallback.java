package com.clay.fallback;

import com.clay.apis.ProviderFeignApi;
import com.clay.resp.ResultData;
import com.clay.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author clay
 * @version 1.0
 */
@Component
public class ProviderFeignApiFallback implements ProviderFeignApi {

    @Override
    public ResultData getPayByOrderNumber(String orderNumber) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "触发服务降级处理，请稍后再试!");
    }

}
