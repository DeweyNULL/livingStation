package com.example.springbootdemo.tool;

import com.example.springbootdemo.controller.Interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MyWebAppConfigurer
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/7 15:27
 * @Version :
 **/

//配置拦截器
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/project-first/**");
    }
}
