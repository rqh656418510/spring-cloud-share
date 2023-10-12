package com.clay.common.base.utils.exception;

import com.clay.common.base.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author clay
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("发生了全局异常...");
    }

    // 特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("发生了算数异常...");
    }

    // 自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R error(CustomException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
