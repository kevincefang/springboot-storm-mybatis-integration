package com.demo.proxy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.dal.mapper.ShareBookMapper;
import com.demo.proxy.service.ShareBooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: kevin.
 * @date: 2017/5/15.
 * @description:
 */
@Service("shareBooksService")
@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class ShareBooksServiceImpl implements ShareBooksService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShareBookMapper shareBookMapper;


    @Override
    public int addShareBook(JSONObject jsonObject) {
        return 0;
    }
}
