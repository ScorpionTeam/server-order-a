package com.kunlun.utils;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }
}
