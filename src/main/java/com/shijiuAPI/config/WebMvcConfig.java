package com.shijiuAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截订单的接口,创建订单-查看订单-查看订单详情这些接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/order/**");
    }

    //    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/index/userinfo")
//                .addPathPatterns("/food/**")
//                .addPathPatterns("/cate/**")
//                .addPathPatterns("/comment/**")
//                .addPathPatterns("/shop/**")
//                .addPathPatterns("/order/**");
//    }
}
