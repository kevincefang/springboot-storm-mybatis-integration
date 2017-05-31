package com.demo.web.adapter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: kevin.
 * @date: 2017/5/23.
 * @description: 客户端请求接口拦截器
 */
public class MethodAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();

        if (!requestUrl.contains(".")){//如果没有任何后缀,自动加上后缀.go
            requestUrl += ".go";
        }
        if(requestUrl.endsWith("go") || requestUrl.contains("swagger-resources") || requestUrl.contains("api-docs")){
            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
