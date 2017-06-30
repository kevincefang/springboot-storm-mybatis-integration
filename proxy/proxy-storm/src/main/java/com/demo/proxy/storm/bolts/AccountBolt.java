package com.demo.proxy.storm.bolts;

import akka.actor.ActorRef;
import com.demo.proxy.storm.akka.demoActor;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class demoBolt extends AkkaSupport {

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);

    }

    @Override
    protected ActorRef createActor(Tuple input, BasicOutputCollector collector, ApplicationContext applicationContext) {
        return actorSystem.actorOf(demoActor.props(input, collector,applicationContext));

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        logger.info("demoBolt.declareOutputFields======");
        declarer.declare(new Fields("demoBolt", "return-info"));
    }
}
