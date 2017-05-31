package com.caiyi.accountbookproxy.service;

import com.demo.proxy.service.ShareBooksService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: kevin.
 * @date: 2017/5/10.
 * @description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
/*
@SpringBootTest(classes= ServiceApplication.class)// 指定spring-boot的启动类
*/
public class ShareBooksServiceTest {


    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ShareBooksService shareBooksService;

    @Test
    public void testQueryShareBooks(){

//        ShareBooks shareBooks = shareBooksService.queryShareBooksById("1");
//        logger.info("shareBooks====="+ JSONObject.toJSONString(shareBooks));
    }
}
