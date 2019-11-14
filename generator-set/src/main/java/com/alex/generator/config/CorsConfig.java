package com.alex.generator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 跨域配置
 * @Author:     alex
 * @CreateDate: 2019/11/14 16:59
 * @Version:    1.0
 *
*/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                //允许请求方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(168000)
                //允许头部设置
                .allowedHeaders("*")
                //允许发送cookie
                .allowCredentials(true);
    }
}
