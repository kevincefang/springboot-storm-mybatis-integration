package com.demo.web.pack.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author: kevin.
 * @date: 2017/5/10.
 * @description: 基础的返回对象
 */
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -2271337320481883018L;

    public static final int CODE_SUCCESS = 1, CODE_FAIL = -1;

    private int code ;

    private String desc;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object results;

    @JsonIgnore
    private ResultMsg resultMsg; //仅用来接收错误码常量类的resultMsg对象

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResultMsg(ResultMsg resultMsg) {
        this.code = resultMsg.getResultCode();
        this.desc = resultMsg.getDesc();
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public ResultMsg getResultMsg() {
        return resultMsg;
    }
}
