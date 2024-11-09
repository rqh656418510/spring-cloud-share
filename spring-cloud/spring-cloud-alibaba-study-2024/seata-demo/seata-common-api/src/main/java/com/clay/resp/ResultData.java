package com.clay.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author clay
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success() {
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(null);
        return resultData;
    }

    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(ReturnCodeEnum code) {
        ResultData resultData = new ResultData();
        resultData.setCode(code.getCode());
        resultData.setMessage(code.getMessage());
        resultData.setData(null);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }

    public boolean isSuccess() {
        return ReturnCodeEnum.RC200.getCode().equals(this.code);
    }

}
