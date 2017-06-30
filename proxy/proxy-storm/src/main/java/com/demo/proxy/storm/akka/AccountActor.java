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


public class demoActor extends BaseActor {

    private static ShareBooksService shareBooksService;


    public demoActor(Tuple tuple, BasicOutputCollector collector,ApplicationContext applicationContext) {
        super(tuple, collector,applicationContext);
        logger.info("demoActor.demoActor() =======");
    }


    public static Props props(final Tuple tuple, final BasicOutputCollector collector,final ApplicationContext applicationContext) {
        return Props.create(new Creator<demoActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public demoActor create() throws Exception {
                return new demoActor(tuple, collector,applicationContext);
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
            if ("demo__signIn".equals(service)){ //签到
                result = signIn(paramStr);
            } else{
                throw new RuntimeException("未知的demo服务");
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
        logger.info("demoActor.signIn shareBookService=="+shareBooksService);
        ShareBook shareBook = shareBooksService.queryShareBookById("2");
        JSONObject resultObject = new JSONObject();
        resultObject.put("code", "1");
        resultObject.put("desc", "调用成功");
        //logger.info("shareBooksService.queryShareBooksById====>>>"+ JSONObject.toJSONString(shareBook));
        logger.info("签到----：" + resultObject.toJSONString());
        return resultObject.toJSONString();
    }


}
