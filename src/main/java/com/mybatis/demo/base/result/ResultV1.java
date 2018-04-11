package com.mybatis.demo.base.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: liyao
 * @Description: 结果对象
 * @Date: Created in 2018/04/10 17:00
 */

public class ResultV1 {
    @JSONField(name = "resultCode")
    private String resultCode = "fail";

    @JSONField(name = "resultData")
    private Object resultData = null;

    @JSONField(name = "version")
    private Long version = null;

    public ResultV1() {
        this("fail", "");
    }

    public ResultV1(String returnCode, Object returnData) {
        if (StringUtils.isEmpty(returnCode)) {
            this.setResultCode("success");
        } else {
            this.setResultCode(returnCode);
        }

        if (returnData == null) {
            this.setResultData("");
        } else {
            this.setResultData(returnData);
        }
    }

    public String getResultCode() {
        return resultCode;
    }

    public ResultV1 setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public Object getResultData() {
        return resultData;
    }

    public ResultV1 setResultData(Object resultData) {
        this.resultData = resultData;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public ResultV1 setVersion(Long version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return this.toJsonString();
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
