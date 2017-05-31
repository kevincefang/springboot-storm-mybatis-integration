package com.demo.web.adapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.web.pack.base.BaseResponse;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: kevin.
 * @date: 2017/5/23.
 * @description: API返回结果JSO
 */
public class CustomJson2HttpMessageConverter extends AbstractHttpMessageConverter<Object> {


    @Override
    protected boolean supports(Class<?> clazz) {
        return BaseResponse.class.isAssignableFrom(clazz);
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object writeObject, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        System.out.println("writeInternal writeObject ===>>"+writeObject);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(writeObject);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",jsonObject.getIntValue("code"));
        resultMap.put("desc",jsonObject.getString("desc"));
        jsonObject.remove("code");
        jsonObject.remove("desc");
        jsonObject.remove("resultMsg");

        if(jsonObject.get("results") != null && jsonObject.get("results").toString().length() > 0){
            resultMap.put("results",jsonObject.get("results"));
        }else {
            jsonObject.remove("results");
            if(jsonObject.size() > 0){
                resultMap.put("results",jsonObject);
            }
        }

        String out = JSON.toJSONString(resultMap);
        outputMessage.getBody().write(out.getBytes());
    }

}
