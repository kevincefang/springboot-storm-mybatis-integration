package com.demo.proxy.storm.serviceconfigs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: kevin.
 * @date: 2017/5/28.
 * @description:
 */
@Component
public class ServiceFactory {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static ThreadLocal<ApplicationContext> applicationContextThreadLocal = new ThreadLocal<>();


    /*@Bean
    public void initApplicationContext(){
        logger.info("initApplicationContext...");
        if(applicationContextThreadLocal.get() == null){
            new SpringApplicationBuilder(Main.class).web(false).run(new String[]{});
            applicationContextThreadLocal.set(BeanUtils.getApplicationContext());
        }
    }*/

    public static Object getBean(String name) {
        return applicationContextThreadLocal.get().getBean(name);
    }


}
