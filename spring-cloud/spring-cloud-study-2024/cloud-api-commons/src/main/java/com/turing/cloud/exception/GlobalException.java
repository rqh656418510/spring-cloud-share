package com.turing.cloud.exception;

import lombok.Data;

/**
 * @author turing
 * @version 1.0
 */
@Data
public class GlobalException extends RuntimeException {

    private String msg;
    private int code = 500;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GlobalException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
