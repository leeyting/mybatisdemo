package com.mybatis.demo.base.exception;

/**
 * @Author: liyao
 * @Description: 自定义Runtime异常类型
 * @Date: Created in 2018/07/15 23:32
 */

public class CustomRuntimeException extends RuntimeException {

    public CustomRuntimeException() {
        super();
    }

    public CustomRuntimeException(String msg) {
        super(msg);
    }

    public CustomRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomRuntimeException(Throwable cause) {
        super(cause);
    }
}
