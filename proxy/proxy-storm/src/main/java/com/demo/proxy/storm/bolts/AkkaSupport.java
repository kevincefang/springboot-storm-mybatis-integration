package com.demo.proxy.storm.bolts;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.demo.proxy.storm.BeanUtils;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by been on 2016/11/4.
 */
public abstract class AkkaSupport extends BaseBasicBolt {

    private static final long serialVersionUID = 1L;

    protected static Logger logger = LoggerFactory.getLogger("AkkaSupport");

    static ActorSystem actorSystem;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        initActorSystem();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                actorSystem.terminate();
            }
        });
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        ActorRef actorRef = createActor(input, collector,BeanUtils.getApplicationContext());
        actorRef.tell("execute", null);
    }

    protected abstract ActorRef createActor(Tuple input, BasicOutputCollector collector, ApplicationContext applicationContext);


    private ActorSystem initActorSystem() {
        synchronized (AkkaSupport.class) {
            if (actorSystem == null) {
                actorSystem = ActorSystem.create("chargeActorSystem");
            }
            return actorSystem;
        }
    }


}
