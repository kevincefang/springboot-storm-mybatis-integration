package com.demo.proxy.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.dal.model.ShareBook;

/**
 * @author: kevin.
 * @date: 2017/5/15.
 * @description: 共享账本Service
 */
public interface ShareBooksService {

    ShareBook queryShareBookById(String cbooksId);


    JSONObject addShareBook(JSONObject jsonObject);

}
