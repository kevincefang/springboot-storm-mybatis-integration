package com.demo.proxy.storm.akka;

import akka.actor.Props;
import akka.japi.Creator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.constants.ProxyErrorCodeConstants;
import com.demo.proxy.exception.BaseException;
import com.demo.proxy.service.SecretKeyService;
import com.demo.proxy.service.ShareBooksService;
import com.demo.proxy.service.ShareMemberService;
import com.demo.proxy.storm.pack.StormBaseResponse;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.springframework.context.ApplicationContext;

/**
 * @author: kevin.
 * @date: 2017/5/19.
 * @description:
 */
public class ShareActor extends BaseActor {

    private static ShareBooksService shareBooksService;

    private static SecretKeyService secretKeyService;

    private static ShareMemberService shareMemberService;



    public ShareActor(Tuple tuple, BasicOutputCollector collector,ApplicationContext applicationContext) {
        super(tuple, collector,applicationContext);
    }

    public static Props props(final Tuple tuple,final BasicOutputCollector collector,ApplicationContext applicationContext){
        return Props.create(new Creator<ShareActor>() {
            private static final long serialVersionUID = 1L;
            @Override
            public ShareActor create() throws Exception {
                return new ShareActor(tuple,collector,applicationContext);
            }
        });
    }

    @Override
    protected void doBusiness(Tuple tuple, BasicOutputCollector collector) {
        StormBaseResponse response = new StormBaseResponse();
        String result = null;
        String paramStr = tuple.getString(0);
        Object returnInfo = tuple.getValue(1);
        logger.info("tuple[0]==>>" + paramStr + ";tuple[1]==>>" + returnInfo);
        JSONObject request = JSONObject.parseObject(paramStr);
        String service = request.getString("service");
        logger.info("ShareActor.doBusiness....service===>>" + service);
        try {
            if ("SHARE_addShareBook".equals(service)){ //添加账本
                response = addShareBook(request);
            }else if("SHARE_removeMember".equals(service)){ //删除成员
                response = removeMember(request);
            } else if ("SHARE_saveSecretKey".equals(service)){ //保存暗号
                response = saveSecretKey(request);
            } else if ("SHARE_querySecretKey".equals(service)){ //查询暗号
                response = querySecretKey(request);
            } else if ("SHARE_joinBook".equals(service)){ //暗号加入账本
                response = joinBook(request);
            }
            else{
                throw new BaseException(ProxyErrorCodeConstants.CODE_UNKNOW_SERVICE);
            }
        } catch (BaseException e){
            response.setCode(e.getCode());
            response.setDesc(e.getDesc());
            logger.error("into BaseException e ==>> "+e);
        } catch(Exception e) {
            response.setResultMsg(ProxyErrorCodeConstants.CODE_SYSTEM_ERROR);
            logger.error("into Exception e ==>> "+e);
        }finally {
            result = JSON.toJSONString(response);
            logger.info("executed ShareActor.business return result ==>>"+result);
            collector.emit(new Values(result, returnInfo));
        }
    }

    @Override
    protected void getServices(ApplicationContext applicationContext) {
        shareBooksService = (ShareBooksService) applicationContext.getBean("shareBooksService");
        secretKeyService = (SecretKeyService) applicationContext.getBean("secretKeyService");
        shareMemberService = (ShareMemberService) applicationContext.getBean("shareMemberService");
    }


    private StormBaseResponse addShareBook(JSONObject request) throws BaseException{
        StormBaseResponse jsonObject = new StormBaseResponse();

        JSONObject resultJson = shareBooksService.addShareBook(request);
        logger.info("addShareBook return result ==>>"+resultJson.toJSONString());
        jsonObject.copyValue(resultJson);
        return jsonObject;
    }


    private StormBaseResponse removeMember(JSONObject request) throws BaseException{
        StormBaseResponse jsonObject = new StormBaseResponse();
        JSONObject  result = shareMemberService.removeMember(request);
        jsonObject.copyValue(result);
        return jsonObject;

    }



    private StormBaseResponse saveSecretKey(JSONObject request) throws BaseException{
        String overTime = secretKeyService.saveSecretKey(request.getString("cbooksId"), request.getString("secretKey"));
        logger.info("saveSecretKey overTime===="+overTime);
        StormBaseResponse resultJson = new StormBaseResponse();
        resultJson.put("overTime", overTime);
        logger.info("into saveSecretKey method========");
        return resultJson;
    }

    private StormBaseResponse querySecretKey(JSONObject request) throws BaseException{

        JSONObject resultJson = secretKeyService.querySecretKey(request.getString("cbooksId"));
        logger.info("querySecretKey resultJson===="+JSON.toJSONString(resultJson));
        StormBaseResponse response = new StormBaseResponse();
        response.copyValue(resultJson);
        return response;

    }

    private StormBaseResponse joinBook(JSONObject request) throws BaseException{
        String cuserId = request.getString("cuserId");
        String secretKey = request.getString("secretKey");
        JSONObject resultJson = secretKeyService.joinShareBookBySecretKey(cuserId, secretKey);
        logger.info("joinBook resultJson=====:" + resultJson.toJSONString());
        StormBaseResponse response = new StormBaseResponse();
        response.copyValue(resultJson);
        return response;
    }
}
