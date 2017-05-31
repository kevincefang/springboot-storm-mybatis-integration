package com.demo.proxy.storm.topology.nirvana.core;

import org.apache.storm.generated.KillOptions;
import org.apache.storm.thrift.TException;
import org.apache.storm.utils.NimbusClient;
import org.apache.storm.utils.Utils;

import java.util.Map;


public class TopologyKiller {

    public static void main(String[] args) {
        String drpcService = args[0];

        Map stormConfig = Utils.readStormConfig();
        NimbusClient nimbus = NimbusClient.getConfiguredClient(stormConfig);
        KillOptions killOptions = new KillOptions();
        killOptions.set_wait_secs(30);
        try {
            nimbus.getClient().killTopologyWithOpts(drpcService, killOptions);
        } catch (TException e) {
            System.err.println("An error occured killing the topology.");
            e.printStackTrace();
        }
    }
}
