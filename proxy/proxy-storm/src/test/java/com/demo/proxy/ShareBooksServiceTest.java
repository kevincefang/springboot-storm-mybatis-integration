package com.demo.proxy;

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
    //private ShareBooksService shareBooksService;

    @Test
    public void testQueryShareBook(){

        //ShareBook shareBooks = shareBooksService.queryShareBookById("2");
        //logger.info("shareBooks====="+ JSONObject.toJSONString(shareBooks));
    }
}
