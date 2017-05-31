package com.caiyi.accountbookproxy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.constants.ProxyErrorCodeConstants;
import com.demo.proxy.exception.BaseException;
import com.demo.proxy.service.SecretKeyService;
import com.demo.proxy.storm.Main;
import com.demo.proxy.storm.pack.StormBaseResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by DONGYA on 2017/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Main.class)// 指定spring-boot的启动类
public class SecretKeyServiceTest {

    @Autowired
    SecretKeyService secretKeyService;

    @Test
    public void testSaveSecretKey(){
        StormBaseResponse response = new StormBaseResponse();
        response.setResultMsg(ProxyErrorCodeConstants.CODE_SUCCESS); //默认成功
        try {
            String overTime = secretKeyService.saveSecretKey("2","332330");
            response = new StormBaseResponse();
            response.put("overTime", overTime);
        }  catch (BaseException e){
            response.setCode(e.getCode());
            response.setDesc(e.getDesc());
        }
        String responseStr = response.toJSONString();
        System.out.println(responseStr);
    }

    @Test
    public void testQuerySecretKey(){
        StormBaseResponse response = new StormBaseResponse();
        response.setResultMsg(ProxyErrorCodeConstants.CODE_SUCCESS); //默认成功
        try {
            JSONObject resultJson = secretKeyService.querySecretKey("4c73cd3863894de29153edf6ffbb627b");
            System.out.println(resultJson.toJSONString());
            response.copyValue(resultJson);
        }  catch (BaseException e){
            response.setCode(e.getCode());
            response.setDesc(e.getDesc());
        }
        String responseStr = JSON.toJSONString(response);
        System.out.println("response" + responseStr);
    }

    @Test
    public void testJoinBook(){
        StormBaseResponse response = new StormBaseResponse();
        response.setResultMsg(ProxyErrorCodeConstants.CODE_SUCCESS); //默认成功
        try {
            JSONObject resultJson = secretKeyService.joinShareBookBySecretKey("aaa", "332330");
            System.out.println(resultJson.toJSONString());
            response.copyValue(resultJson);
        }  catch (BaseException e){
            response.setCode(e.getCode());
            response.setDesc(e.getDesc());
        }
        String responseStr = JSON.toJSONString(response);
        System.out.println("response" + responseStr);
    }
}
