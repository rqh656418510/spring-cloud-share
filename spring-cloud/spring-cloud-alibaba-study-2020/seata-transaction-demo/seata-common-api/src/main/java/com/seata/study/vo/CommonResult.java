package com.seata.study.vo;

import com.seata.study.enums.SystemCode;

import java.io.Serializable;

/**
 * @author clay
 * @version 1.0
 */
public class CommonResult<T> implements Serializable {

    private SystemCode code;

    private String message;

    private T data;

    public CommonResult() {
        this.code = SystemCode.BUSINESS_OK;
        this.message = code.getDescription();
    }

    public CommonResult(SystemCode code) {
        this.code = code;
        this.message = code.getDescription();
    }

    public CommonResult(SystemCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(SystemCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public SystemCode getCode() {
        return code;
    }

    public void setCode(SystemCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
