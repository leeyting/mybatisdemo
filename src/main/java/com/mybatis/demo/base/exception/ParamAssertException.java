package com.mybatis.demo.base.exception;

/**
 * @Author: liyao
 * @Description: 自定义异常
 * @Date: Created in 2018/04/30 22:16
 */
public class ParamAssertException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParamAssertException() {
		super();
	}

	public ParamAssertException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParamAssertException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamAssertException(String message) {
		super(message);
	}

	public ParamAssertException(Throwable cause) {
		super(cause);
	}

}
