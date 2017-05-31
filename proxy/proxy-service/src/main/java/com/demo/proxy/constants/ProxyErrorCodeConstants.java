package com.demo.proxy.constants;

/**
 * @author: kevin.
 * @date: 2017/5/18.
 * @description:
 */
public class ProxyErrorCodeConstants {

    public static final ResultMsg CODE_SUCCESS = getRetResult(1,"操作成功");
    public static final ResultMsg CODE_FAIL = getRetResult(-1,"操作失败");
    public static final ResultMsg CODE_UNKNOW_SERVICE = getRetResult(-2,"未知的storm服务");

    public static final ResultMsg CODE_PARAMS_ERROR = getRetResult(-1001,"参数错误");
    public static final ResultMsg CODE_SYSTEM_ERROR = getRetResult(-1002,"系统异常");
    public static final ResultMsg CODE_NET_ERROR = getRetResult(-1003,"网络异常");



    private static ResultMsg getRetResult(int code, String desc){
        return new ResultMsg(code,desc);

    }
}
