package com.turing.cloud.exception;

import com.turing.cloud.resp.ResultData;
import com.turing.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author turing
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exceptionHandler(Exception e) {
        // 日志记录
        log.error("Global exception message：{}", e.getMessage(), e);

        if (e instanceof GlobalException) {
            // 处理自定义的全局异常
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        } else if (e instanceof BindException) {
            // 处理基于SpringMVC的JSR303参数校验失败的情况
            return ResultData.fail(ReturnCodeEnum.RC380.getCode(), e.getMessage());
        } else {
            // 处理其他未知的全局异常
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
    }

}