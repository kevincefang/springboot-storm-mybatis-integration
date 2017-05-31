package com.caiyi.accountbookproxy.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;


/**
 * @author: kevin.
 * @date: 2017/5/12.
 * @description:
 */
public class BeanUtil {

    /**
     * 获取Spring注入的类
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     * @throws ClassCastException
     */
    public static <T> T getBean(Class requiredType) throws BeansException, ClassCastException {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        return (T) context.getBean(requiredType);
    }

    /**
     * 获取通过name注入的类
     * @param beanName
     * @param <T>
     * @return
     * @throws BeansException
     * @throws ClassCastException
     */
    public static <T> T getBean(String beanName) throws BeansException, ClassCastException  {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        System.out.println("BeanUtil.getBean.context======="+context);
        return (T) context.getBean(beanName);
    }

}
