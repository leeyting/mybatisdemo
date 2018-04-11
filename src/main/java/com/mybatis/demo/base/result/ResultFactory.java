package com.mybatis.demo.base.result;

/**
 * @Author: liyao
 * @Description: 结果工厂类
 * @Date: Created in 2018/04/10 16:59
 */

public final class ResultFactory {
    public static ResultV1 FailedV1(Object resultData) {
        return new ResultV1("failed", FormatEmptyObject(resultData, ""));
    }

    public static ResultV1 SuccessV1(Object resultData) {
        return new ResultV1("success", FormatEmptyObject(resultData, ""));
    }

    public static ResultV1 LoginErrorV1(Object resultData) {
        return new ResultV1("loginError", FormatEmptyObject(resultData, ""));
    }

    public static ResultV1 ExpiredV1(Object resultData) {
        return new ResultV1("expired", FormatEmptyObject(resultData, ""));
    }

    private static Object FormatEmptyObject(Object value, String defaultStr) {
        if (value == null) {
            return defaultStr;
        } else {
            return value;
        }
    }
}
