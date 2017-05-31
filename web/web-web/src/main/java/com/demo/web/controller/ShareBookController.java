package com.demo.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.web.constants.ResultErrorCodeConstants;
import com.demo.web.document.ShareBookDocResponse;
import com.demo.web.pack.req.ShareBookRequest;
import com.demo.web.pack.resp.AddShareBookResponse;
import com.demo.web.storm.drpc.DRPCConstants;
import com.demo.web.storm.drpc.DRPCSupport;
import com.mina.rbc.util.CheckUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin.
 * @date: 2017/5/19.
 * @description:
 */
@RestController
@RequestMapping(value="/sharebook")
public class ShareBookController extends  BaseController{


    @ApiOperation(value="创建XX", notes="创建XX",tags = "XX",response = ShareBookDocResponse.class)
    @RequestMapping(value = "add_share" , method = RequestMethod.POST)
    @ResponseBody
    public AddShareBookResponse add(@ModelAttribute ShareBookRequest shareBookRequest){
        AddShareBookResponse resp = new AddShareBookResponse();

        if(shareBookRequest.getCuserId() == null || shareBookRequest.getCbookName() == null){
            resp.setResultMsg(ResultErrorCodeConstants.CODE_PARAMS_ERROR);
            return resp;
        }
        try {
            resp.setResultMsg(ResultErrorCodeConstants.CODE_ADD_SHAREBOOK_ERROR);
            shareBookRequest.setService("SHARE_addShareBook");
            logger.info("start invoke storm SHARE_addShareBook");
            String callStr = (String) DRPCSupport.doBussiness(DRPCConstants.TOPOLOGY_NAME,JSON.toJSONString(shareBookRequest),stormConfig).get(DRPCConstants.RESULT);
            logger.info("storm SHARE_addShareBook return result ：" + callStr);
            if (CheckUtil.isNullString(callStr)){
                resp.setResultMsg(ResultErrorCodeConstants.CODE_STORM_FAIL);
            } else {
                resp = JSONObject.parseObject(callStr, AddShareBookResponse.class);
            }
        } catch (Exception e) {
            logger.error("网络异常",e);
            resp.setResultMsg(ResultErrorCodeConstants.CODE_NET_ERROR);
        }
        return resp;

    }

}
