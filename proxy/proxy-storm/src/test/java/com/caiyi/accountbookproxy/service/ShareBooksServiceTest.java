package com.caiyi.accountbookproxy.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.dal.model.ShareBook;
import com.demo.proxy.service.ShareBooksService;
import com.demo.proxy.storm.Main;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: kevin.
 * @date: 2017/5/10.
 * @description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Main.class)// 指定spring-boot的启动类
public class ShareBooksServiceTest {


    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ShareBooksService shareBooksService;

    @Test
    public void testQueryShareBook(){

        ShareBook shareBooks = shareBooksService.queryShareBookById("2");
        logger.info("shareBooks====="+ JSONObject.toJSONString(shareBooks));
    }

    @Test
    public void testAddShareBook(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cuserId","dcf2eceb-9db8-425e-8d2e-a1689487267e");
        jsonObject.put("cbookName","book6");
        jsonObject.put("cbookColor","red");
        jsonObject.put("iparentType","2");
        jsonObject.put("cwriteDate","2017/05/21 21:32:00");
        jsonObject.put("operatorType","1");
        System.out.println("======"+shareBooksService.addShareBook(jsonObject));
    }
}
