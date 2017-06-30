package com.demo.proxy.storm.akka;

import akka.actor.UntypedActor;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


public abstract class BaseActor extends UntypedActor {
    public static Logger logger = LoggerFactory.getLogger("BaseActor");

    private final ApplicationContext applicationContext;

    private final Tuple tuple;
    private final BasicOutputCollector collector;

    BaseActor(Tuple tuple, BasicOutputCollector collector,ApplicationContext applicationContext) {
        this.tuple = tuple;
        this.collector = collector;
        this.applicationContext = applicationContext;
        logger.info("BaseActor.BaseActor() applicationContext ====="+this.applicationContext);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        try {
            String msg = (String) message;
            if (msg.equals("execute")) {
                logger.info("BaseActor.onReceive=====");
                getServices(this.applicationContext);
                doBusiness(tuple, collector);
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        } finally {
            context().stop(self());
        }
    }

    protected abstract void doBusiness(Tuple tuple, BasicOutputCollector collector);

    protected abstract void getServices(ApplicationContext applicationContext);

}
