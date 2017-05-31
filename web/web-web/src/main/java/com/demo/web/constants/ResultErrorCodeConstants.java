package com.demo.web.constants;

import com.demo.web.pack.base.ResultMsg;

/**
 * @author: kevin.
 * @date: 2017/5/10.
 * @description:
 */
public class ResultErrorCodeConstants {

    public static final ResultMsg CODE_SUCCESS = getRetResult(1,"请求成功");
    public static final ResultMsg CODE_QUERY_SUCCESS = getRetResult(1,"查询成功");

    public static final ResultMsg CODE_FAIL = getRetResult(-1,"请求失败");
    public static final ResultMsg CODE_PARAMS_ERROR = getRetResult(1001,"参数错误");
    public static final ResultMsg CODE_SYSTEM_ERROR = getRetResult(1002,"系统异常");
    public static final ResultMsg CODE_NET_ERROR = getRetResult(1003,"网络异常");
    public static final ResultMsg CODE_STORM_FAIL = getRetResult(8888,"Storm服务异常");

    public static final ResultMsg CODE_SIGN_ERROR = getRetResult(2001,"签到失败");
    public static final ResultMsg CODE_ADD_SHAREBOOK_ERROR = getRetResult(2002,"添加共享账本失败");
    public static final ResultMsg CODE_JOINBOOK_ERROR = getRetResult(2003,"加入共享账本异常");



    private static ResultMsg getRetResult(int code, String desc){
        return new ResultMsg(code,desc);

    }


}
