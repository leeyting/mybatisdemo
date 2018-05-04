package com.mybatis.demo.base.utils;

import com.alibaba.fastjson.JSONArray;

/**
 * @Author: liyao
 * @Description: 自定义JSONArray
 * @Date: Created in 2018/05/04 10:12
 */

public class MyJSONArray extends JSONArray {

    private String arrayName;

    public MyJSONArray() {
    }

    public MyJSONArray(String arrayName) {
        this.arrayName = arrayName;
    }

    public MyJSONArray addObj(Object e) {
        super.add(e);
        return this;
    }

    public String getArrayName() {
        return arrayName;
    }

    public void setArrayName(String arrayName) {
        this.arrayName = arrayName;
    }
}
