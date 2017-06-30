package com.demo.proxy.dal.model;

import java.util.Date;

/**
 * @author: kevin.
 * @date: 2017/5/15.
 * @description:
 */
public class ShareBook {

    private String cbooksid; //账本ID

    private String ccreator;//创建人ID

    private String cadmin;//管理员ID

    private String cbooksname;//账本名称

    private String cbookscolor;//账本颜色

    private String iparenttype;//账本父类型

    private Date cadddate;//账本添加时间

    private String csecretkey;//暗号

    private String covertime;//暗号过期时间

    private Long iversion;//版本号

    private String cwritedate;//客户端操作时间

    private String operatortype;//操作类型

    public String getCbooksid() {
        return cbooksid;
    }

    public void setCbooksid(String cbooksid) {
        this.cbooksid = cbooksid;
    }

    public String getCcreator() {
        return ccreator;
    }

    public void setCcreator(String ccreator) {
        this.ccreator = ccreator;
    }

    public String getCadmin() {
        return cadmin;
    }

    public void setCadmin(String cadmin) {
        this.cadmin = cadmin;
    }

    public String getCbooksname() {
        return cbooksname;
    }

    public void setCbooksname(String cbooksname) {
        this.cbooksname = cbooksname;
    }

    public String getCbookscolor() {
        return cbookscolor;
    }

    public void setCbookscolor(String cbookscolor) {
        this.cbookscolor = cbookscolor;
    }

    public String getIparenttype() {
        return iparenttype;
    }

    public void setIparenttype(String iparenttype) {
        this.iparenttype = iparenttype;
    }

    public Date getCadddate() {
        return cadddate;
    }

    public void setCadddate(Date cadddate) {
        this.cadddate = cadddate;
    }

    public String getCsecretkey() {
        return csecretkey;
    }

    public void setCsecretkey(String csecretkey) {
        this.csecretkey = csecretkey;
    }

    public String getCovertime() {
        return covertime;
    }

    public void setCovertime(String covertime) {
        this.covertime = covertime;
    }

    public Long getIversion() {
        return iversion;
    }

    public void setIversion(Long iversion) {
        this.iversion = iversion;
    }

    public String getCwritedate() {
        return cwritedate;
    }

    public void setCwritedate(String cwritedate) {
        this.cwritedate = cwritedate;
    }

    public String getOperatortype() {
        return operatortype;
    }

    public void setOperatortype(String operatortype) {
        this.operatortype = operatortype;
    }
}
