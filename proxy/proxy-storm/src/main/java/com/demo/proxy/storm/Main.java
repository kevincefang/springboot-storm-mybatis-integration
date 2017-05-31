package com.demo.proxy.storm;

import com.demo.proxy.storm.topology.ChargeBookTopology;
import com.caiyi.accountbookproxy.util.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ApplicationObjectSupport;


/**
 * Main
 */
@Configuration
@EnableAutoConfiguration
//@ServletComponentScan
@ComponentScan(basePackages="com.caiyi.accountbookproxy")
public class Main extends ApplicationObjectSupport {
    private static Logger logger = LoggerFactory.getLogger("Main");


    public static void main(String[] args) throws Exception {

        if (args.length < 1 || (!args[0].equals("pro")) && (!args[0].equals("dev")) && (!args[0].equals("local"))) {
            throw new Exception("\n\t请输入参数:\n\tpro:生产环境运行\n\tdev:开发环境运行\n\tlocal:本地模拟集群运行");
        }
        logger.info("开始部署拓扑...");
        ChargeBookTopology ptp = new ChargeBookTopology();
        if (args[0].equals("pro")) {
            ptp.submitStorm();
            logger.info("已部署在生产环境");
        } else if (args[0].equals("dev")) {
            ptp.submitDevStorm();
            logger.info("已部署在开发环境");
        } else if (args[0].equals("local")) {
            ptp.submitLocal();
            logger.info("已部署在本地环境");
        }
        //ptp.submitLocal();
    }


    @Bean
    public ApplicationContextAware applicationContextAware() {
        ApplicationContextProvider applicationContextProvider =  new ApplicationContextProvider();
        logger.info("Main.applicationContextAware======"+applicationContextProvider);
        return applicationContextProvider;
    }
}
