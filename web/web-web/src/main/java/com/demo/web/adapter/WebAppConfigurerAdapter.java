package com.demo.web.adapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: kevin.
 * @date: 2017/5/19.
 * @description: WebMvc配置适配器
 */
@Configuration
public class WebAppConfigurerAdapter extends WebMvcConfigurerAdapter {

    public WebAppConfigurerAdapter(){
        super();
    }

    @Bean
    public CustomJson2HttpMessageConverter resultJSONConverter(){
        CustomJson2HttpMessageConverter converter = new CustomJson2HttpMessageConverter();
        List<MediaType> mediaTypes = new LinkedList<>();
        mediaTypes.add(org.springframework.http.MediaType.APPLICATION_JSON);
        mediaTypes.add(org.springframework.http.MediaType.IMAGE_JPEG);
        mediaTypes.add(org.springframework.http.MediaType.IMAGE_PNG);
        mediaTypes.add(org.springframework.http.MediaType.IMAGE_GIF);
        mediaTypes.add(org.springframework.http.MediaType.TEXT_PLAIN);
        mediaTypes.add(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
        mediaTypes.add(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
        mediaTypes.add(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
        mediaTypes.add(org.springframework.http.MediaType.TEXT_HTML);
        mediaTypes.add(org.springframework.http.MediaType.ALL);
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(resultJSONConverter());
        super.configureMessageConverters(converters);
    }

    @Bean
    public MethodAccessInterceptor methodAccessInterceptor(){
        return new MethodAccessInterceptor();
    }

    /**
     * 配置拦截器
     * @author lance
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(methodAccessInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
        //ir.excludePathPatterns("/swagger-ui.html");
        super.addInterceptors(registry);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
