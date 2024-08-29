package com.clay.limit.exception;

public class RedisLimitException extends RuntimeException {

    public RedisLimitException() {
    }

    public RedisLimitException(String message) {
        super(message);
    }

    public RedisLimitException(String message, Throwable cause) {
        super(message, cause);
    }

}
