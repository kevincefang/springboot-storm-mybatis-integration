package com.demo.web.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: kevin.
 * @date: 2017/5/10.
 * @description: 请求参数日志切面
 */
@Aspect
@Component
public class RequestAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.caiyi.accountbookweb.controller..*.*(..))")
    private void requestAspect() {}

    // 请求method前打印内容
    @Before(value = "requestAspect()")
    public void methodBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 打印请求内容
        logger.info("|===============请求内容 start ===============|");
        logger.info("|请求地址:" + request.getRequestURL().toString());
        logger.info("|请求方式:" + request.getMethod());
        logger.info("|请求类方法:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //获取所有参数
        Enumeration<String> enu = request.getParameterNames();
        StringBuilder params = new StringBuilder("【");
        while(enu.hasMoreElements()){
            String paraName = enu.nextElement();
            params.append(paraName).append("=").append(request.getParameter(paraName)).append(";");
        }
        params.append("】");
        logger.info("|请求类方法参数:" + params.toString());
        logger.info("|===============请求内容  end  ===============|");
    }

    @AfterReturning(returning = "result", pointcut = "requestAspect()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容
        String resultJson = null;
        if(result != null){
            resultJson = JSON.toJSONString(result);
        }
        logger.info("执行结果response==>> " + resultJson);
        logger.info("请求耗时==>> " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }

}
