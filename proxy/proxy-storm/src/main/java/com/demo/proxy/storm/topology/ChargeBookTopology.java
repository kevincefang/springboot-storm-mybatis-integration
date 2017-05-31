package com.demo.proxy.storm.topology;

import com.alibaba.fastjson.JSONObject;
import com.demo.proxy.storm.bolts.AccountBolt;
import com.demo.proxy.storm.bolts.PreBolt;
import com.demo.proxy.storm.bolts.ShareBolt;
import com.caiyi.financial.nirvana.heartbeat.server.spout.DynamicDRPCSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.LocalDRPC;
import org.apache.storm.StormSubmitter;
import org.apache.storm.drpc.DRPCSpout;
import org.apache.storm.drpc.ReturnResults;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ChargeBookTopology {

    private Logger logger;
    private TopologyBuilder builder;
    private Config conf;
    private DRPCSpout drpc;

    public ChargeBookTopology() {
        logger = LoggerFactory.getLogger(ChargeBookTopology.class);
        builder = new TopologyBuilder();
        drpc = new DynamicDRPCSpout("chargebookDRPC");
        conf = new Config();
    }

    public TopologyBuilder getTopologyBuilder(){
        return builder;
    }

    private void init() {
        builder.setSpout("chargebookSpout", drpc);
        builder.setBolt("preBolt", new PreBolt(), 4).shuffleGrouping("chargebookSpout");
        builder.setBolt("accountBolt", new AccountBolt(), 2).shuffleGrouping("preBolt", "accountBolt");
        builder.setBolt("shareBolt",new ShareBolt(),2).shuffleGrouping("preBolt","shareBolt");
        builder.setBolt("return", new ReturnResults(), 2)
           .shuffleGrouping("accountBolt").shuffleGrouping("shareBolt");
    }

    public void submitLocal() {
        this.init();
        String JVM_OPTS = "-Xmx1024m";
        conf.put(Config.TOPOLOGY_WORKER_CHILDOPTS, JVM_OPTS);
        conf.put(Config.TOPOLOGY_ACKER_EXECUTORS,0);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("local-test", conf, builder.createTopology());
    }

    public void submitDevStorm() {
        this.init();
        try {
            String JVM_OPTS = "-Xms700m -Xmx700m -XX:NewRatio=3 -XX:SurvivorRatio=8 -XX:+PrintTenuringDistribution -XX:+DisableExplicitGC";
            conf.setNumWorkers(4);
            conf.put(Config.TOPOLOGY_WORKER_CHILDOPTS, JVM_OPTS);
            conf.put(Config.TOPOLOGY_ACKER_EXECUTORS,0);
            StormSubmitter.submitTopology("storm-chargebook-topology", conf, builder.createTopology());
            logger.info("[Storm Topology] : Submitted");
        } catch (Exception e) {
            logger.error("Storm Topology : Submitted Error:err " + e.toString());
        }
    }

    public void submitStorm() {
        this.init();
        try {
            String JVM_OPTS = "-Xms2048m -Xmx2048m -XX:NewRatio=3 -XX:SurvivorRatio=8 -XX:+PrintTenuringDistribution -XX:+DisableExplicitGC";
            conf.setNumWorkers(4);
            conf.put(Config.TOPOLOGY_WORKER_CHILDOPTS, JVM_OPTS);
            conf.put(Config.TOPOLOGY_ACKER_EXECUTORS,0);
            StormSubmitter.submitTopology("storm-chargebook-topology", conf, builder.createTopology());
            logger.info("[Storm Topology] : Submitted");
        } catch (Exception e) {
            logger.error("Storm Topology : Submitted Error:err " + e.toString());
        }
    }


    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(2);
        String JVM_OPTS = "-Xmx4096m";
        if (args == null || args.length == 0) {
            LocalDRPC drpc = new LocalDRPC();
            DynamicDRPCSpout drpcSpout = new DynamicDRPCSpout("chargebookSpout", drpc);
            builder.setSpout("chargebookSpout", drpcSpout);
            builder.setBolt("preBolt", new PreBolt()).shuffleGrouping("chargebookSpout");
            builder.setBolt("accountBolt", new AccountBolt()).shuffleGrouping("preBolt", "accountBolt");
            builder.setBolt("return", new ReturnResults(), 4)
                    .shuffleGrouping("userBolt").shuffleGrouping("accountBolt");

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("manual-drpc-demo", conf, builder.createTopology());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", "2a738180-38b3-4edc-acb4-91185034ed0e");

            jsonObject.put("service", "ACCOUNT__signIn");
//			jsonObject.put("auth_token", "JJDLSN22CINCJEW");
//			jsonObject.put("type", "qq");
//			jsonObject.put("cpwd", "sdsadsadsadsad");


            String ret = drpc.execute("chargebookSpout", jsonObject.toJSONString());

            System.out.println("################Result for " + ret + " :" + ret);

            cluster.shutdown();
            drpc.shutdown();
        } else {
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
