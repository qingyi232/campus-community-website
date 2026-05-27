package com.campus.community.config;

import com.campus.community.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/index",
                        "/login", "/doLogin",
                        "/register", "/doRegister",
                        "/club/list", "/club/detail/**",
                        "/activity/list", "/activity/detail/**",
                        "/news/list", "/news/detail/**",
                        "/notice/list", "/notice/detail/**",
                        "/message/list",
                        "/css/**", "/js/**", "/images/**",
                        "/error"
                );
    }
}
