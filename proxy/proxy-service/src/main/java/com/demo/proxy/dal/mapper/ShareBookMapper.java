package com.demo.proxy.dal.mapper;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.dal.model.ShareBook;
import org.springframework.stereotype.Repository;

/**
 * @author: kevin.
 * @date: 2017/5/15.
 * @description:
 */
@Repository
public interface ShareBookMapper {

    ShareBook queryShareBookById(String cbookId);

    Integer addShareBook(ShareBook shareBook);

    void updateSecretKey(JSONObject paramJson);

    int updateBooksAdminById(String cbooksId);

    /**
     * 通过暗号获取账本
     * @param secretKey
     * @return
     */
    ShareBook queryShareBookBySecretKey(String secretKey);
}
