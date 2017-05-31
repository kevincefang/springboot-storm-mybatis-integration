package com.demo.proxy.enums;

/**
 * @author: kevin.
 * @date: 2017/5/26.
 * @description: 操作类型枚举类
 */
public enum OperateType {

    ADD("0","新增"),
    UPDATE("1","修改"),
    REMOVE("2","删除");

    private OperateType(String state, String desc){
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
