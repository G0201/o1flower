//package com.example.flower.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private JwtAuthenticationInterceptor jwtAuthenticationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtAuthenticationInterceptor)
//                .addPathPatterns("/api/**") // 需要拦截的路径
//                .excludePathPatterns("/api/login", "/api/register"); // 排除的路径
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*") // 根据需求配置
//                .allowedMethods("*")
//                .allowedHeaders("*");
//    }
//}
