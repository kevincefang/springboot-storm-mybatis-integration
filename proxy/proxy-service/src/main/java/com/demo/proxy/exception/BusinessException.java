package com.demo.proxy.exception;


/**
 * 业务异常
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }
    public BusinessException(int code, String desc) {
        super();

    }

    public BusinessException(ResultMsg resultMsg){
        super();
        this.code = resultMsg.getResultCode();
        this.desc = resultMsg.getDesc();
    }

    public BusinessException(String desc){
        super(desc);
    }

}
