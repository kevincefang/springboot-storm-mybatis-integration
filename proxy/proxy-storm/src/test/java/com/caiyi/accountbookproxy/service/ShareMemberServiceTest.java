package com.caiyi.accountbookproxy.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.constants.ResultMsg;
import com.demo.proxy.dal.model.ShareMember;
import com.demo.proxy.dal.model.UserCharge;
import com.demo.proxy.service.ShareMemberService;
import com.demo.proxy.storm.Main;
import com.demo.proxy.storm.pack.StormBaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)// 指定spring-boot的启动类
public class ShareMemberServiceTest {
    private Logger logger = LoggerFactory.getLogger(ShareMemberServiceTest.class);

    @Autowired
    private ShareMemberService service;

    @Test
    public void addShareMember() {
        ShareMember member = new ShareMember();
        member.setCbooksid("5c8e78ba719344bc8f555d3d09a613a1");
        member.setCmemberid("f72836e1-4552-495b-9bdd-0358945cd115");
        member.setIstate(0);
        JSONObject object = service.addShareMember(member.getCbooksid(), member.getCmemberid());
        System.out.println(object);
//           List<ShareMember> members = service.addShareMember(JSON.parseObject(JSON.toJSONString(member)));
        logger.info("添加共享成员返回[" + object + "]");
    }

    @Test
    public void removeMember() {
        StormBaseResponse response = new StormBaseResponse();

        String cmemberId = "f4a2456c-3b6a-4b62-9d2d-31ea1012032b";
        String cbooksid = "ffc0be1334644f0db0347893b2921327";
        JSONObject object = new JSONObject();
        object.put("cmemberid", cmemberId);
        object.put("cbooksid", cbooksid);
        object.put("istate", "1");

        JSONObject object1 = service.removeMember(object);
        System.out.println(object1);
        response.copyValue(object1);
        System.out.println(JSON.toJSONString(response));
        RemoveMemberResponse removeMemberResponse = JSONObject.parseObject(object1.toJSONString(), RemoveMemberResponse.class);
        System.out.println(removeMemberResponse);
        //  System.out.println((StormBaseResponse)object1);
    }


}


class RemoveMemberResponse extends BaseResponse{
    private List<ShareMember> share_member;
    private List<UserCharge> share_charge;

    public List<ShareMember> getShare_member() {
        return share_member;
    }

    public void setShare_member(List<ShareMember> share_member) {
        this.share_member = share_member;
    }

    public List<UserCharge> getShare_charge() {
        return share_charge;
    }

    public void setShare_charge(List<UserCharge> share_charge) {
        this.share_charge = share_charge;
    }
}

class BaseResponse implements Serializable {

    private static final long serialVersionUID = -2271337320481883018L;

    public static final int CODE_SUCCESS = 1, CODE_FAIL = -1;

    private int code ;

    private String desc;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object results;

    private ResultMsg resultMsg; //仅用来接收错误码常量类的resultMsg对象

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

    public void setResultMsg(ResultMsg resultMsg) {
        this.code = resultMsg.getResultCode();
        this.desc = resultMsg.getDesc();
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    @JsonIgnore
    public ResultMsg getResultMsg() {
        return resultMsg;
    }
}

