package com.caiyi.accountbookproxy.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: kevin.
 * @date: 2017/5/12.
 * @description:
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    static ApplicationContext getApplicationContext() {
        return ac;
    }
}
