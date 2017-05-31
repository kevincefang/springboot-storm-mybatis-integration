package com.demo.proxy.storm.akka;

import akka.actor.Props;
import akka.japi.Creator;
import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.dal.model.ShareBook;
import com.demo.proxy.service.ShareBooksService;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.springframework.context.ApplicationContext;


/**
 * Created by Admin on 2016/12/8.
 * 记账其他业务处理actor
 */
public class AccountActor extends BaseActor {

    private static ShareBooksService shareBooksService;


    public AccountActor(Tuple tuple, BasicOutputCollector collector,ApplicationContext applicationContext) {
        super(tuple, collector,applicationContext);
        logger.info("AccountActor.AccountActor() =======");
    }


    public static Props props(final Tuple tuple, final BasicOutputCollector collector,final ApplicationContext applicationContext) {
        return Props.create(new Creator<AccountActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public AccountActor create() throws Exception {
                return new AccountActor(tuple, collector,applicationContext);
            }
        });
    }

    @Override
    protected void doBusiness(Tuple input, BasicOutputCollector collector) {
        Object returnInfo = input.getValue(1);
        String result = null;
        try {
            String paramStr = input.getString(0);
            logger.info("string[0]=======>>"+paramStr + ";string[1]=======>>"+returnInfo);
            JSONObject object = JSONObject.parseObject(paramStr);
            String service = object.getString("service");
            if ("ACCOUNT__signIn".equals(service)){ //签到
                result = signIn(paramStr);
            } else{
                throw new RuntimeException("未知的account服务");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONObject resultObject = new JSONObject();
            resultObject.put("code", "-3333");
            resultObject.put("desc", "系统异常");
            result = resultObject.toJSONString();
        } finally {
            collector.emit(new Values(result, returnInfo));
        }
    }

    @Override
    protected void getServices(ApplicationContext applicationContext) {
       shareBooksService = (ShareBooksService) applicationContext.getBean("shareBooksService");
    }


    private String signIn(String paramStr){ //签到
        logger.info("AccountActor.signIn shareBookService=="+shareBooksService);
        ShareBook shareBook = shareBooksService.queryShareBookById("2");
        JSONObject resultObject = new JSONObject();
        resultObject.put("code", "1");
        resultObject.put("desc", "调用成功");
        //logger.info("shareBooksService.queryShareBooksById====>>>"+ JSONObject.toJSONString(shareBook));
        logger.info("签到----：" + resultObject.toJSONString());
        return resultObject.toJSONString();
    }


}
