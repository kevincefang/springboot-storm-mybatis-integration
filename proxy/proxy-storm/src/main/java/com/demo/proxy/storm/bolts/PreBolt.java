package com.demo.proxy.storm.bolts;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.dal.model.ShareBook;
import com.demo.proxy.service.ShareBooksService;
import com.demo.proxy.storm.BeanUtils;
import com.demo.proxy.storm.Main;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * 前置bolt所有业务都通过本入口进行分发
 *
 */
public class PreBolt extends BaseBasicBolt {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger("PreBolt");

    private static ApplicationContext applicationContext = null;


    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);


        logger.info("Main start...");
        new SpringApplicationBuilder(Main.class).web(false).run(new String[]{});
        logger.info("Main end...");

        applicationContext = BeanUtils.getApplicationContext();
        logger.info("Main end...getApplicationContext==="+ BeanUtils.getApplicationContext());
        //logger.info("转换applicationContext为jsonString====="+JSON.toJSONString(BeanUtils.getApplicationContext()));

        ShareBooksService shareBooksService = (ShareBooksService) BeanUtils.getBean("shareBooksService");

        logger.info("Prebolt shareBooksService====="+shareBooksService);
        ShareBook shareBook = shareBooksService.queryShareBookById("2");
        logger.info("shareBooksService.queryShareBooksById====>>>"+ JSONObject.toJSONString(shareBook));

    }


    public void execute(Tuple input, BasicOutputCollector collector) {
        logger.info("======preBolt.execute context1===="+ BeanUtils.getApplicationContext());

        String param = input.getString(0);
        logger.info("PreBolt.execute request param ==>> " + param);
        JSONObject object = JSONObject.parseObject(param);
        String service = object.getString("service");
        logger.info("preBolt request service ==>> " + service);
        Object returnInfo = input.getValue(1);
        if (service.startsWith("SHARE_")) { //共享记账模块
            logger.info("start into  shareBolt applicationContext====="+applicationContext);
            collector.emit("shareBolt", new Values(param, returnInfo));
        } else if(service.startsWith("demo_")){ //记账其他业务模块
            logger.info("start into  demoBolt applicationContext====="+applicationContext);
            collector.emit("demoBolt", new Values(param, returnInfo));
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        logger.info("PreBolt.declareOutputFields======");
        declarer.declareStream("shareBolt", new Fields("shareBolt", "return-info")); //共享记账
        declarer.declareStream("demoBolt", new Fields("demoBolt", "return-info")); //第三方

    }

}

