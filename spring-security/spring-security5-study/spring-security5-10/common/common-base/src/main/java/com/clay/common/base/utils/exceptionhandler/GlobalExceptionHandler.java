package com.clay.common.base.utils.exceptionhandler;

import com.clay.common.base.utils.utils.R;
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
        log.error(e.getMessage());
        return R.error().message("执行了全局异常处理...");
    }

    // 特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        log.error(e.getMessage());
        return R.error().message("执行了ArithmeticException异常处理...");
    }

    // 自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R error(CustomException e) {
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
