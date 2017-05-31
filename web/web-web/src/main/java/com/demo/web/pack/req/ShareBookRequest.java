package com.demo.web.pack.req;

import com.demo.web.pack.base.BaseRequest;

/**
 * @author: kevin.
 * @date: 2017/5/19.
 * @description:
 */
public class ShareBookRequest extends BaseRequest {


    private String cbookName;//账本名称

    private String cbookColor;//账本颜色

    private String iparentType;//账本父类型

    private String cwriteDate;//客户端操作时间

    private String operatorType;//操作类型

    public String getCbookName() {
        return cbookName;
    }

    public void setCbookName(String cbookName) {
        this.cbookName = cbookName;
    }

    public String getCbookColor() {
        return cbookColor;
    }

    public void setCbookColor(String cbookColor) {
        this.cbookColor = cbookColor;
    }

    public String getIparentType() {
        return iparentType;
    }

    public void setIparentType(String iparentType) {
        this.iparentType = iparentType;
    }

    public String getCwriteDate() {
        return cwriteDate;
    }

    public void setCwriteDate(String cwriteDate) {
        this.cwriteDate = cwriteDate;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }
}
