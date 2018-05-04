package com.mybatis.demo.base.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: liyao
 * @Description: 自定义JSONObject
 * @Date: Created in 2018/05/04 09:59
 */

public class MyJSONObject extends JSONObject {

    private String objName;

    public MyJSONObject() {
        super();
    }

    public MyJSONObject(boolean ordered) {
        super(16, ordered);
    }

    public MyJSONObject(String objName) {
        this.objName = objName;
    }

    public MyJSONObject put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}
