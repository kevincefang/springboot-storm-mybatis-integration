package com.demo.proxy.exception;


import com.demo.proxy.constants.ResultMsg;

/**
 * Created by DONGYA on 2017/5/23.
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
