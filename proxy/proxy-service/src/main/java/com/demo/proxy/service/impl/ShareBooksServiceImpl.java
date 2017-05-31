package com.demo.proxy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.caiyi.accountbookproxy.util.Utils;
import com.demo.proxy.constants.ProxyErrorCodeConstants;
import com.demo.proxy.dal.mapper.ShareBookMapper;
import com.demo.proxy.dal.model.ShareBook;
import com.demo.proxy.exception.BusinessException;
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
    public ShareBook queryShareBookById(String cbookId) {
        return shareBookMapper.queryShareBookById(cbookId);
    }

    @Override
    public JSONObject addShareBook(JSONObject jsonObject) throws BusinessException{
        try{
            JSONObject result = new JSONObject();

            String cuserId = jsonObject.getString("cuserId");
            String cbookId = Utils.generateUUID();

            //返回结果
            result.put("code",1);
            result.put("desc","添加账本成功");

            return result;
        }catch (Exception e){
            logger.error("ShareBooksServiceImpl.addShareBook occur an Exception",e);
            throw new BusinessException(ProxyErrorCodeConstants.CODE_FAIL);
        }
    }





}
