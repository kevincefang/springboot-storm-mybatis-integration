package com.demo.proxy.enums;

/**
 * @author: kevin.
 * @date: 2017/5/26.
 * @description: 状态类型枚举类
 */
public enum StateType {

    NORMAL("1","正常"),
    EXIT("0","退出"),
    REMOVE("-1","移除");


    private StateType(String state,String desc){
        this.state = state;
        this.desc = desc;
    }

    private String state;

    private String desc;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
