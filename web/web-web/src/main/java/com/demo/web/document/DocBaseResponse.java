package com.demo.web.document;

import java.io.Serializable;

/**
 * @author: kevin.
 * @date: 2017/5/24.
 * @description:
 */
public class DocBaseResponse implements Serializable {

    private static final long serialVersionUID = 8233697927899917743L;

    public int code;

    public String desc;


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
