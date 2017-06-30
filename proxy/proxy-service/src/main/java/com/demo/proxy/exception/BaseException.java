package com.demo.proxy.exception;

public class BaseException extends RuntimeException {

    public int code;
    public String desc;
    public BaseException() {
        super();
    }
    public BaseException(int code, String desc) {
        super();
        this.code = code;
        this.desc = desc;
    }

    public BaseException(ResultMsg resultMsg){
        super();
        this.code = resultMsg.getResultCode();
        this.desc = resultMsg.getDesc();
    }

    public BaseException(String desc){
        super(desc);
        this.desc = desc;
    }



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
}
