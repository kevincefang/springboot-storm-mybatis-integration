package com.demo.web.storm.drpc;

import com.demo.web.storm.StormConfig;
import org.apache.storm.thrift.transport.TTransportException;
import org.apache.storm.utils.DRPCClient;

import java.util.HashMap;
import java.util.Map;

public class DrpcClientFactory {

    private StormConfig stormConfig;

    public DrpcClientFactory(StormConfig stormConfig) {
        this.stormConfig = stormConfig;
    }

    static DrpcClientFactory getInstance(StormConfig stormConfig){
        return new DrpcClientFactory(stormConfig);
    }

    //300 秒超时
    DRPCClient getDefaultDRPCClient() throws TTransportException {
        return new DRPCClient(getConf(), stormConfig.getHost(), stormConfig.getPort(), stormConfig.getTimeout());
    }

    private Map getConf() {
        Map<String, Object> conf = new HashMap<>();
        conf.put("storm.thrift.transport", stormConfig.getTransport());
        conf.put("storm.nimbus.retry.times", stormConfig.getTimeout());
        conf.put("storm.nimbus.retry.interval.millis", stormConfig.getIntervalMillis());
        conf.put("storm.nimbus.retry.intervalceiling.millis", stormConfig.getIntervalceilingMillis());
        conf.put("drpc.max_buffer_size", stormConfig.getBufferSize());
        return conf;
    }

}
