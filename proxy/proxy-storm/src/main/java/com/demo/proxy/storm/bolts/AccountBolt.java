package com.demo.proxy.storm.bolts;

import akka.actor.ActorRef;
import com.demo.proxy.storm.akka.AccountActor;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by Admin on 2016/12/9.
 */
public class AccountBolt extends AkkaSupport {

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);

    }

    @Override
    protected ActorRef createActor(Tuple input, BasicOutputCollector collector, ApplicationContext applicationContext) {
        return actorSystem.actorOf(AccountActor.props(input, collector,applicationContext));

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        logger.info("AccountBolt.declareOutputFields======");
        declarer.declare(new Fields("accountBolt", "return-info"));
    }
}
