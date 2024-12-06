package com.shijiu.config;

import com.shijiu.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置 这样配置 只有电脑的几个可以访问
//        registry.addMapping("/**").allowedOrigins(
//                "http://localhost:8080",
//                "http://localhost:9529",
//                "http://localhost:9528");

        //这样之后 手机也可以访问,连到同一个wifi即可
        registry.addMapping("/**").allowedOrigins("*");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/index/userinfo")
                .addPathPatterns("/food/**")
                .addPathPatterns("/cate/**")
                .addPathPatterns("/comment/**")
                .addPathPatterns("/shop/**")
                .addPathPatterns("/order/**");
    }
}
