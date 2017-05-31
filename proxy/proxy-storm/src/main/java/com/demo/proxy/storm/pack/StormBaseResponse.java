package com.demo.proxy.storm.pack;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.constants.ProxyErrorCodeConstants;
import com.demo.proxy.constants.ResultMsg;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author: kevin.
 * @date: 2017/5/19.
 * @description: Storm返回调用结果
 */
public class StormBaseResponse extends JSONObject implements Serializable{

    public StormBaseResponse(){ //默认成功
        setResultMsg(ProxyErrorCodeConstants.CODE_SUCCESS);
    }

    private static final long serialVersionUID = 7681010142636240558L;

    private int code;

    private String desc;


    @JsonIgnore
    private ResultMsg resultMsg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.put("code", code);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        this.put("desc", desc);
    }



    public void setResultMsg(ResultMsg resultMsg) {
        setCode(resultMsg.getResultCode());
        setDesc(resultMsg.getDesc());
    }

    public void copyValue(JSONObject source){
        for(String key:source.keySet()){
            this.put(key, source.get(key));
        }

    }




}
