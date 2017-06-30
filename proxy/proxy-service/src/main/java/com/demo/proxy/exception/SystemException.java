package com.demo.proxy.exception;

import com.demo.proxy.constants.ResultMsg;

/**
 * 系统异常(程序异常)
 */
public class SystemException extends BaseException{
    public SystemException() {
        super();
    }
    public SystemException(int code, String desc) {
        super();
    }

    public SystemException(ResultMsg resultMsg){
        super();
        this.code = resultMsg.getResultCode();
        this.desc = resultMsg.getDesc();
    }

    public SystemException(String desc){
        super(desc);
    }
}
