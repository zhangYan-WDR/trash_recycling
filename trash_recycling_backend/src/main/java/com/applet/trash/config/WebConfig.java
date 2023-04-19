package com.applet.trash.config;

import com.applet.trash.interceptor.UserInfoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(getUserInfoInterceptor());
        log.info("===========   拦截器注册完毕   ===========");
    }

    @Bean
    public HandlerInterceptor getUserInfoInterceptor(){
        return new UserInfoInterceptor();
    }

}