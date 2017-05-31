package com.demo.proxy.constants;

/**
 * @author: kevin.
 * @date: 2017/5/10.
 * @description: 错误码和错误码提示
 */
public class ResultMsg {

    private int code;

    private String desc;

    public ResultMsg(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getResultCode() {
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
}
