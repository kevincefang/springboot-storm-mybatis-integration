package com.demo.proxy.storm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Main
 */
@Configuration
@EnableAutoConfiguration
//@ServletComponentScan
@ComponentScan(basePackages="com.demo.proxy")
public class Main{
    private static Logger logger = LoggerFactory.getLogger("Main");


    public static void main(String[] args) throws Exception {

        if (args.length < 1 || (!args[0].equals("pro")) && (!args[0].equals("dev")) && (!args[0].equals("local"))) {
            throw new Exception("\n\t请输入参数:\n\tpro:生产环境运行\n\tdev:开发环境运行\n\tlocal:本地模拟集群运行");
        }
        logger.info("开始部署拓扑...");
        //ChargeBookTopology ptp = new ChargeBookTopology();
        if (args[0].equals("pro")) {
            //ptp.submitStorm();
            logger.info("已部署在生产环境");
        } else if (args[0].equals("dev")) {
            //ptp.submitDevStorm();
            logger.info("已部署在开发环境");
        } else if (args[0].equals("local")) {
            //ptp.submitLocal();
            logger.info("已部署在本地环境");
        }
        //ptp.submitLocal();
    }

}
